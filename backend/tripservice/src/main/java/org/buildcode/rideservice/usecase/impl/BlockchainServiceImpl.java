package org.buildcode.rideservice.usecase.impl;

import lombok.extern.slf4j.Slf4j;
import org.buildcode.rideservice.api.config.EthereumConfig;
import org.buildcode.rideservice.contracts.RideCreation;
import org.buildcode.rideservice.data.entity.Ride;
import org.buildcode.rideservice.usecase.BlockchainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.gas.DefaultGasProvider;

@Service
@Slf4j
public class BlockchainServiceImpl implements BlockchainService {

    private final RideCreation rideCreationContract;

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