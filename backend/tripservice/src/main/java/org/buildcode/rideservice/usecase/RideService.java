package org.buildcode.rideservice.usecase;

import org.buildcode.rideservice.api.model.v1_0.CreateRideRequestModel;
import org.buildcode.rideservice.api.model.v1_0.RideResponseModel;
import org.buildcode.rideservice.data.entity.Ride;

public interface RideService {

    Ride createRide(CreateRideRequestModel createRideRequestModel);

    RideResponseModel getRideById(String id);

    Boolean deleteById(String id);
}