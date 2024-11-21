package org.buildcode.rideservice;

import org.buildcode.rideservice.contracts.RideCreation;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.crypto.Credentials;
import org.web3j.tx.gas.DefaultGasProvider;
import java.math.BigInteger;

public class TestRideCreation {

    public static void main(String[] args) {
        try {
            // Connect to the Ethereum network
            Web3j web3j = Web3j.build(new HttpService("http://localhost:8545"));

            // Use the credentials of the account deploying the contract
            Credentials credentials = Credentials.create("0xa8dc07fb6eeb9fc38d89495e21d464b22ab01f69086decac8a75052c2ce87ad1");

            // Deployed contract address
            String contractAddress = "0x71DF4fa8767f275e113757c6123b4712d6BFD866";

            // Create a contract instance
            RideCreation rideCreationContract = RideCreation.load(
                    contractAddress, web3j, credentials, new DefaultGasProvider()
            );

            // Create a new ride using the `Struct0` data structure
            RideCreation.Struct0 rideDetails = new RideCreation.Struct0(
                    "ride123",               // rideId (String)
                    "user456",               // userId (String)
                    "PickupLocation",        // source (String)
                    "DropOffLocation",       // destination (String)
                    BigInteger.valueOf(50),  // fare (BigInteger)
                    BigInteger.valueOf(3),   // availableSeats (BigInteger)
                    "Toyota Camry"           // carModel (String)
            );

            // Call the `createRide` function
            RemoteCall<TransactionReceipt> transaction = rideCreationContract.createRide(rideDetails);

            // Send the transaction and get the receipt
            TransactionReceipt receipt = transaction.send();

            System.out.println("Ride created with transaction hash: " + receipt.getTransactionHash());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
