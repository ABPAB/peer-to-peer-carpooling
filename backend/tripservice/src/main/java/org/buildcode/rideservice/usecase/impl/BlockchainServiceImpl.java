package org.buildcode.rideservice.usecase.impl;

import lombok.extern.slf4j.Slf4j;
import org.buildcode.rideservice.api.constants.KafkaConstants;
import org.buildcode.rideservice.api.constants.RideStatus;
import org.buildcode.rideservice.contracts.RideCreation;
import org.buildcode.rideservice.data.dto.RideEventPayload;
import org.buildcode.rideservice.data.dto.RideNotificationPayload;
import org.buildcode.rideservice.data.entity.Ride;
import org.buildcode.rideservice.usecase.BlockchainService;
import org.buildcode.rideservice.usecase.EventHandlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.gas.DefaultGasProvider;

@Service
@Slf4j
public class BlockchainServiceImpl implements BlockchainService {

    private final String contractAddress = "0x27E642aC8Eb62f19213A6e3D0C90AB7e487593c1";

    private final String blockchainKey = "0xa75f16141f24f45d92ed785fb1180d64718901beb59637005c586bb21103fe38";

    private final RideCreation rideCreationContract;

    private final EventHandlerService eventHandlerService;

    private final Web3j web3j;

    @Autowired
    public BlockchainServiceImpl(
            EventHandlerService eventHandlerService,
            Web3j web3j
    ) {
        this.eventHandlerService = eventHandlerService;
        this.web3j = web3j;

        Credentials credentials = Credentials.create(blockchainKey);
        this.rideCreationContract = RideCreation.load(
                contractAddress,
                web3j,
                credentials,
                new DefaultGasProvider()
        );

        log.info("BlockchainServiceImpl initialized with contract address: {}", contractAddress);
    }

    @Override
    public TransactionReceipt createRide(Ride rideDetails) {
        try {
            log.info("Creating a new ride on the blockchain...");
            TransactionReceipt transactionReceipt = rideCreationContract.createRide(
                    rideDetails.getId(),
                    rideDetails.getOwnerId(),
                    rideDetails.getSource(),
                    rideDetails.getDestination(),
                    rideDetails.getFare(),
                    rideDetails.getSeats(),
                    rideDetails.getVehicleNumber()
            ).send();

            log.info("Ride created successfully with transaction hash: {}", transactionReceipt.getTransactionHash());

            rideCreationContract.rideCreatedEventFlowable(
                    DefaultBlockParameter.valueOf(transactionReceipt.getBlockNumber()),
                    DefaultBlockParameter.valueOf(transactionReceipt.getBlockNumber())
            ).subscribe(rideCreatedEvent -> {
                log.info("Captured RideCreatedEvent: {}", rideCreatedEvent);

                RideEventPayload payload = RideEventPayload.builder()
                        .rideId(rideCreatedEvent.rideId)
                        .ownerId(rideCreatedEvent.ownerId)
                        .source(rideCreatedEvent.source)
                        .destination(rideCreatedEvent.destination)
                        .fare(rideCreatedEvent.fare)
                        .seats(rideCreatedEvent.availableSeats)
                        .vehicleNumber(rideCreatedEvent.vehicleNumber)
                        .rideStatus(RideStatus.fromValue(rideCreatedEvent.status))
                        .build();

                eventHandlerService.handleRideCreatedEvent(payload, KafkaConstants.RIDE_CREATED_TOPIC);

            }, error -> log.error("Error while capturing RideCreatedEvent: {}", error.getMessage(), error));

            return transactionReceipt;

        } catch (Exception e) {
            log.error("Error occurred while creating a ride: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to create ride on the blockchain", e);
        }
    }

    @Override
    public TransactionReceipt acceptRideRequest(String rideId, String ownerId, String riderId) {
        try {
            log.info("Accepting ride request on the blockchain...");
            TransactionReceipt transactionReceipt = rideCreationContract.acceptRideByOwner(rideId, ownerId, riderId).send();

            log.info("Ride request accepted successfully. Transaction hash: {}", transactionReceipt.getTransactionHash());

            rideCreationContract.rideUpdatedEventFlowable(
                    DefaultBlockParameter.valueOf(transactionReceipt.getBlockNumber()),
                    DefaultBlockParameter.valueOf(transactionReceipt.getBlockNumber())
            ).subscribe(rideAcceptedEvent -> {
                log.info("Captured RideUpdatedEvent: {}", rideAcceptedEvent);

                RideEventPayload payload = RideEventPayload.builder()
                        .rideId(rideAcceptedEvent.rideId)
                        .ownerId(rideAcceptedEvent.ownerId)
                        .seats(rideAcceptedEvent.availableSeats)
                        .updatedAt(rideAcceptedEvent.updatedAt)
                        .rideStatus(RideStatus.fromValue(rideAcceptedEvent.status))
                        .build();

                eventHandlerService.handleRideUpdatedEvent(payload);

            }, error -> log.error("Error while capturing RideUpdatedEvent: {}", error.getMessage(), error));

            rideCreationContract.sendNotificationEventEventFlowable(
                    DefaultBlockParameter.valueOf(transactionReceipt.getBlockNumber()),
                    DefaultBlockParameter.valueOf(transactionReceipt.getBlockNumber())
            ).subscribe(notificationEvent -> {
                log.info("Captured NotificationEvent: {}", notificationEvent);

                RideNotificationPayload notificationPayload = RideNotificationPayload.builder()
                        .riderId(notificationEvent.riderId)
                        .ownerId(notificationEvent.ownerId)
                        .destination(notificationEvent.destination)
                        .fare(notificationEvent.fare)
                        .source(notificationEvent.source)
                        .vehicleNumber(notificationEvent.vehicleNumber)
                        .riderStatus(notificationEvent.status)
                        .build();

                eventHandlerService.handleNotificationEvent(notificationPayload);

            }, error -> log.error("Error while capturing NotificationEvent: {}", error.getMessage(), error));

            return transactionReceipt;

        } catch (Exception e) {
            log.error("Error occurred while accepting the ride request: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to accept ride request on the blockchain", e);
        }
    }

    @Override
    public TransactionReceipt cancelRide(String rideId, String ownerId) {
        try {
            log.info("Cancelling ride with ID: {} by owner ID: {}", rideId, ownerId);

            // Call the smart contract to update ride status to CANCELLED
            TransactionReceipt transactionReceipt = rideCreationContract
                    .updateRideStatusByDriver(rideId, ownerId, RideStatus.CANCELLED.getValue())
                    .send();

            log.info("Ride cancelled successfully. Transaction hash: {}", transactionReceipt.getTransactionHash());

            // Subscribe to the RideUpdatedEvent to handle blockchain events related to the ride update
            rideCreationContract.rideUpdatedEventFlowable(
                    DefaultBlockParameter.valueOf(transactionReceipt.getBlockNumber()),
                    DefaultBlockParameter.valueOf(transactionReceipt.getBlockNumber())
            ).subscribe(rideUpdatedEvent -> {
                log.info("Captured RideUpdatedEvent: {}", rideUpdatedEvent);

                // Build the Kafka payload
                RideEventPayload payload = RideEventPayload.builder()
                        .rideId(rideUpdatedEvent.rideId)
                        .ownerId(rideUpdatedEvent.ownerId)
                        .seats(rideUpdatedEvent.availableSeats)
                        .updatedAt(rideUpdatedEvent.updatedAt)
                        .rideStatus(RideStatus.fromValue(rideUpdatedEvent.status))
                        .build();

                // Delegate event handling to EventHandlerService
                eventHandlerService.handleRideUpdatedEvent(payload);

            }, error -> log.error("Error while capturing RideUpdatedEvent: {}", error.getMessage(), error));

            rideCreationContract.sendNotificationEventEventFlowable(
                    DefaultBlockParameter.valueOf(transactionReceipt.getBlockNumber()),
                    DefaultBlockParameter.valueOf(transactionReceipt.getBlockNumber())
            ).subscribe(notificationEvent -> {
                log.info("Captured NotificationEvent: {}", notificationEvent);

                RideNotificationPayload notificationPayload = RideNotificationPayload.builder()
                        .riderId(notificationEvent.riderId)
                        .ownerId(notificationEvent.ownerId)
                        .destination(notificationEvent.destination)
                        .fare(notificationEvent.fare)
                        .source(notificationEvent.source)
                        .vehicleNumber(notificationEvent.vehicleNumber)
                        .riderStatus(notificationEvent.status)
                        .build();

                eventHandlerService.handleNotificationEvent(notificationPayload);

            }, error -> log.error("Error while capturing NotificationEvent: {}", error.getMessage(), error));

            return transactionReceipt;

        } catch (Exception e) {
            log.error("Error occurred while cancelling the ride with ID {}: {}", rideId, e.getMessage(), e);
            throw new RuntimeException("Failed to cancel the ride on the blockchain", e);
        }
    }

}
