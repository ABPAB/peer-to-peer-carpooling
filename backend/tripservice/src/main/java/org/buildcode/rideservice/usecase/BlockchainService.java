package org.buildcode.rideservice.usecase;

import org.buildcode.rideservice.data.dto.RideCreatedResponsePayload;
import org.buildcode.rideservice.data.entity.Ride;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

public interface BlockchainService {

    RideCreatedResponsePayload createRide(Ride rideDetails);

    TransactionReceipt acceptRideRequest(String rideId, String ownerId, String riderId);

    TransactionReceipt cancelRide(String rideId, String ownerId);
}