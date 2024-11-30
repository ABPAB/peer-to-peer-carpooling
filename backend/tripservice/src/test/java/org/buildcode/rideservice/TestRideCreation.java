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
            Credentials credentials = Credentials.create("0x1dcc502094122f4d4f12bed6bb38ee66dda84dd2e98da146dfd3228d9f2d4dba");

            // Deployed contract address
            String contractAddress = "0xf40b110E9857efEb5455F82959bD07BFC229315B";

            // Create a contract instance
            RideCreation rideCreationContract = RideCreation.load(
                    contractAddress, web3j, credentials, new DefaultGasProvider()
            );

            String rideId = "ride12333"; // Dummy Ride ID
            String ownerId = "owner001"; // Dummy Owner ID
            String source = "Location A"; // Dummy Source
            String destination = "Location B"; // Dummy Destination
            long fare = 1000; // Dummy Fare in Wei (1 ETH = 1e18 Wei)
            int availableSeats = 4; // Dummy number of available seats
            String vehicleNumber = "XYZ1234"; // Dummy Vehicle Number

            // Call the createRide function
            RemoteCall<TransactionReceipt> transaction = rideCreationContract.createRide(rideId, ownerId, source, destination, BigInteger.valueOf(fare), BigInteger.valueOf(availableSeats), vehicleNumber, "12:33PM", "23/10/26");
//            RemoteCall<TransactionReceipt> update = rideCreationContract.updateRideStatusByDriver(rideId, ownerId, BigInteger.valueOf(1));
//            RemoteCall<TransactionReceipt> update = rideCreationContract.acceptRideByOwner(rideId, ownerId, "rider1234");
//            RemoteCall<TransactionReceipt> update = rideCreationContract.cancelRideByRider(rideId, "rider1234");
//            RemoteCall<BigInteger> update = rideCreationContract.getRiderStatus(rideId, "rider1234");
            TransactionReceipt receipt = transaction.send();
//            BigInteger receipt = update.send();
            System.out.println("Ride created with transaction hash: " + receipt);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}