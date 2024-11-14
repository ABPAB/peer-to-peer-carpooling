package org.buildcode.rideservice.data.mapper;

import org.buildcode.rideservice.api.model.v1_0.CreateRideRequestModel;
import org.buildcode.rideservice.api.model.v1_0.RideResponseModel;
import org.buildcode.rideservice.data.entity.Ride;
import org.springframework.stereotype.Component;

@Component
public class RideMapper {

    public Ride toRide(CreateRideRequestModel createRideRequestModel) {
        Ride ride = new Ride();
        ride.setSource(createRideRequestModel.getSource());
        ride.setDestination(createRideRequestModel.getDestination());
        ride.setUserId(createRideRequestModel.getUserId());
        ride.setSeats(createRideRequestModel.getSeats());
        ride.setCarModel(createRideRequestModel.getCarModel());
        return ride;
    }

    public RideResponseModel toRideResponseModel(Ride ride) {
        RideResponseModel responseModel = new RideResponseModel();
        responseModel.setId(ride.getId());
        responseModel.setSource(ride.getSource());
        responseModel.setDestination(ride.getDestination());
        responseModel.setUserId(ride.getUserId());
        responseModel.setSeats(ride.getSeats());
        responseModel.setCarModel(ride.getCarModel());
        responseModel.setStatus(ride.getStatus());
        responseModel.setCreatedAt(ride.getCreatedAt());
        responseModel.setUpdatedAt(ride.getUpdatedAt());
        return responseModel;
    }
}