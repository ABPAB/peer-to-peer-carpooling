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
        // Connect to the Ganache or other Ethereum network
        Web3j web3j = Web3j.build(new HttpService("http://localhost:8545"));

        // Use the credentials of the account deploying the contract
        Credentials credentials = Credentials.create("0xd3f038195db4b4754948b0d9d62b5785cfce98bf4b167dd99f260cc6d0210200");

        // Deployed contract address
        String contractAddress = "0x4499B6e8413307edb96eFC7C14D5e9BF29d94093";

        // Create a contract instance
        RideCreation rideCreationContract = RideCreation.load(
                contractAddress, web3j, credentials, new DefaultGasProvider()
        );

        // Example: Calling createRide function
        try {
            RemoteCall<TransactionReceipt> transaction = rideCreationContract.createRide(
                    "PickupLocation",
                    "DropOffLocation",
                    BigInteger.valueOf(50),
                    BigInteger.valueOf(3)
            );

            TransactionReceipt receipt = transaction.send();

            System.out.println("Ride created with transaction hash: " + receipt.getTransactionHash());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
