package org.buildcode.rideservice;

import org.buildcode.rideservice.contracts.RideCreation;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.crypto.Credentials;
import org.web3j.tuples.generated.Tuple10;
import org.web3j.tx.gas.DefaultGasProvider;
import java.math.BigInteger;

public class TestRideCreation {

    public static void main(String[] args) {
        try {
            // Connect to the Ethereum network
            Web3j web3j = Web3j.build(new HttpService("http://localhost:8545"));

            // Use the credentials of the account deploying the contract
            Credentials credentials = Credentials.create("0x3c9f11bd5043775abe2b65494c19ea4fae820bb7f96a52dc061da1c4bc288aa5");

            // Deployed contract address
            String contractAddress = "0x97C4FB587C660Fb0410578c94278377fEb80243A";

            // Create a contract instance
            RideCreation rideCreationContract = RideCreation.load(
                    contractAddress, web3j, credentials, new DefaultGasProvider()
            );

            String rideId = "ride123"; // Dummy Ride ID
            String ownerId = "owner001"; // Dummy Owner ID
            String source = "Location A"; // Dummy Source
            String destination = "Location B"; // Dummy Destination
            long fare = 1000; // Dummy Fare in Wei (1 ETH = 1e18 Wei)
            int availableSeats = 4; // Dummy number of available seats
            String vehicleNumber = "XYZ1234"; // Dummy Vehicle Number

            // Call the `createRide` function
//            RemoteCall<TransactionReceipt> transaction = rideCreationContract.createRide(rideId, ownerId, source, destination, BigInteger.valueOf(fare), BigInteger.valueOf(availableSeats), vehicleNumber);
//            RemoteCall<TransactionReceipt> update = rideCreationContract.updateRideStatusByDriver(rideId, ownerId, BigInteger.valueOf(1));
//            RemoteCall<TransactionReceipt> update = rideCreationContract.acceptRideByOwner(rideId, ownerId, "rider1234");
//            RemoteCall<TransactionReceipt> update = rideCreationContract.cancelRideByRider(rideId, "rider1234");
            RemoteCall<BigInteger> update = rideCreationContract.getRiderStatus(rideId, "rider1234");
//            TransactionReceipt receipt = update.send();
            BigInteger receipt = update.send();
            System.out.println("Ride created with transaction hash: " + receipt);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
