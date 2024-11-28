package org.buildcode.rideservice.usecase;

import org.buildcode.rideservice.data.entity.Ride;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

public interface BlockchainService {

    TransactionReceipt createRide(Ride rideDetails);

    TransactionReceipt acceptRideRequest(String rideId, String ownerId, String riderId);
}