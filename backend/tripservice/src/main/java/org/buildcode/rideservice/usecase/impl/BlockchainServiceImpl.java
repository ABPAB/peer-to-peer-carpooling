package org.buildcode.rideservice.usecase.impl;

import lombok.extern.slf4j.Slf4j;
import org.buildcode.rideservice.api.constants.KafkaConstants;
import org.buildcode.rideservice.api.constants.RideStatus;
import org.buildcode.rideservice.contracts.RideCreation;
import org.buildcode.rideservice.data.dto.RideCreatedResponsePayload;
import org.buildcode.rideservice.data.dto.RideEventPayload;
import org.buildcode.rideservice.data.dto.RideNotificationPayload;
import org.buildcode.rideservice.data.entity.Ride;
import org.buildcode.rideservice.usecase.BlockchainService;
import org.buildcode.rideservice.usecase.EventHandlerService;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.gas.DefaultGasProvider;

@Service
@Slf4j
public class BlockchainServiceImpl implements BlockchainService {

    private final String contractAddress = "0xE7906562A5D2c9Af983203E7831048d9199F7A33";

    private final String blockchainKey = "0x1dcc502094122f4d4f12bed6bb38ee66dda84dd2e98da146dfd3228d9f2d4dba";

    private final RideCreation rideCreationContract;

    private final EventHandlerService eventHandlerService;

    private final Web3j web3j;


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
    public RideCreatedResponsePayload createRide(Ride rideDetails) {
        try {
            log.info("Creating a new ride on the blockchain...");

//            TransactionReceipt transactionReceipt = rideCreationContract.createRide(
//                    rideDetails.getId(),
//                    rideDetails.getOwnerId(),
//                    rideDetails.getSource(),
//                    rideDetails.getDestination(),
//                    rideDetails.getFare(),
//                    rideDetails.getSeats(),
//                    rideDetails.getVehicleNumber(),
//                    rideDetails.getDepartureTime(),
//                    rideDetails.getDepartureDate()
//            ).send();

//            log.info("Ride created successfully with transaction hash: {}", transactionReceipt.getTransactionHash());

            log.info("sending ride data to kafka: {}", rideDetails);
            eventHandlerService.handleRideCreatedEvent(rideDetails, KafkaConstants.RIDE_CREATED_TOPIC);

            log.info("sending notification payload: {}", rideDetails);
            eventHandlerService.handleNotificationEvent(rideDetails, KafkaConstants.SEND_NOTIFICATION_EVENT);

            RideCreatedResponsePayload responsePayload = RideCreatedResponsePayload.builder()
                    .availableSeats(rideDetails.getSeats())
                    .departureDate(rideDetails.getDepartureDate())
                    .departureTime(rideDetails.getDepartureTime())
                    .destination(rideDetails.getDestination())
                    .fare(rideDetails.getFare())
                    .source(rideDetails.getSource())
                    .vehicleNumber(rideDetails.getVehicleNumber())
                    .rideId(rideDetails.getId())
                    .build();

            return responsePayload;
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

                eventHandlerService.handleNotificationEvent(notificationPayload, KafkaConstants.SEND_NOTIFICATION_EVENT);

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

                eventHandlerService.handleNotificationEvent(notificationPayload, KafkaConstants.SEND_NOTIFICATION_EVENT);

            }, error -> log.error("Error while capturing NotificationEvent: {}", error.getMessage(), error));

            return transactionReceipt;

        } catch (Exception e) {
            log.error("Error occurred while cancelling the ride with ID {}: {}", rideId, e.getMessage(), e);
            throw new RuntimeException("Failed to cancel the ride on the blockchain", e);
        }
    }
}