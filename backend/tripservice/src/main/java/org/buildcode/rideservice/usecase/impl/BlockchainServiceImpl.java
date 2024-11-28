package org.buildcode.rideservice.usecase.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.buildcode.rideservice.api.config.EthereumConfig;
import org.buildcode.rideservice.api.constants.KafkaConstants;
import org.buildcode.rideservice.contracts.RideCreation;
import org.buildcode.rideservice.data.dto.RideCreatedEventPayload;
import org.buildcode.rideservice.data.entity.Ride;
import org.buildcode.rideservice.usecase.BlockchainService;
import org.buildcode.rideservice.usecase.KafkaEventProducerService;
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

    private final RideCreation rideCreationContract;

    @Autowired
    private KafkaEventProducerService kafkaEventProducerService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    public BlockchainServiceImpl(Web3j web3j, EthereumConfig ethereumConfig) {
        // Initializes RideCreation contract using EthereumConfig and Web3j
        Credentials credentials = Credentials.create(ethereumConfig.getBlockchainKey());
        this.rideCreationContract = RideCreation.load(
                ethereumConfig.getContractAddress(),
                web3j,
                credentials,
                new DefaultGasProvider()
        );

        log.info("BlockchainServiceImpl initialized with contract address: {}", ethereumConfig.getContractAddress());
    }

    @Override
    public TransactionReceipt createRide(Ride rideDetails) {
        try {
            log.info("Creating a new ride on the blockchain...");
            // Create the ride on the blockchain
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

            // Capture the RideCreatedEvent from the blockchain
            rideCreationContract.rideCreatedEventFlowable(
                    DefaultBlockParameter.valueOf(transactionReceipt.getBlockNumber()),
                    DefaultBlockParameter.valueOf(transactionReceipt.getBlockNumber())
            ).subscribe(rideCreatedEvent -> {
                try {
                    log.info("Captured RideCreatedEvent: {}", rideCreatedEvent);

                    // Build payload
                    RideCreatedEventPayload payload = RideCreatedEventPayload.builder()
                            .rideId(rideCreatedEvent.rideId)
                            .ownerId(rideCreatedEvent.ownerId)
                            .source(rideCreatedEvent.source)
                            .destination(rideCreatedEvent.destination)
                            .fare(rideCreatedEvent.fare)
                            .seats(rideCreatedEvent.availableSeats)
                            .vehicleNumber(rideCreatedEvent.vehicleNumber)
                            .build();

                    // Serialize payload to JSON
                    String eventPayloadJson = objectMapper.writeValueAsString(payload);

                    // Send the event to Kafka
                    kafkaEventProducerService.sendEvent(KafkaConstants.RIDE_CREATED_TOPIC, rideCreatedEvent.rideId, eventPayloadJson);

                    log.info("Event sent to Kafka topic '{}' for ride ID: {}", KafkaConstants.RIDE_CREATED_TOPIC, rideCreatedEvent.rideId);
                } catch (Exception e) {
                    log.error("Error while processing RideCreatedEvent for ride ID: {}: {}", rideCreatedEvent.rideId, e.getMessage(), e);
                }
            }, error -> {
                log.error("Error while capturing RideCreatedEvent: {}", error.getMessage(), error);
            });

            return transactionReceipt;

        } catch (Exception e) {
            log.error("Error occurred while creating a ride: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to create ride on the blockchain", e);
        }
    }


    @Override
    public TransactionReceipt bookRideRequest() {
        return null;
    }
}