package org.buildcode.rideservice.data.mapper;

import org.buildcode.rideservice.api.model.v1_0.CreateRideRequestModel;
import org.buildcode.rideservice.api.model.v1_0.RideResponseModel;
import org.buildcode.rideservice.data.dto.RideEventPayload;
import org.buildcode.rideservice.data.entity.Ride;
import org.springframework.stereotype.Component;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

@Component
public class RideMapper {

    public Ride toRide(CreateRideRequestModel createRideRequestModel, String uuid) {
        Ride ride = new Ride();
        ride.setId(uuid);
        ride.setSource(createRideRequestModel.getSource());
        ride.setDestination(createRideRequestModel.getDestination());
        ride.setOwnerId(createRideRequestModel.getUserId());
        ride.setSeats(createRideRequestModel.getSeats());
        ride.setVehicleNumber(createRideRequestModel.getCarModel());
        ride.setFare(createRideRequestModel.getFare());
        return ride;
    }

    public RideResponseModel toRideResponseModel(Ride ride) {
        RideResponseModel responseModel = new RideResponseModel();
        responseModel.setId(ride.getId());
        responseModel.setSource(ride.getSource());
        responseModel.setDestination(ride.getDestination());
        responseModel.setUserId(ride.getOwnerId());
        responseModel.setSeats(ride.getSeats());
        responseModel.setVehicleNumber(ride.getVehicleNumber());
        responseModel.setStatus(ride.getStatus());
        responseModel.setCreatedAt(ride.getCreatedAt());
        responseModel.setUpdatedAt(ride.getUpdatedAt());
        responseModel.setFare(ride.getFare());
        return responseModel;
    }

    public RideResponseModel toRideResponseModel(RideEventPayload rideEventPayload) {
        RideResponseModel responseModel = new RideResponseModel();
        responseModel.setId(rideEventPayload.getRideId());
        responseModel.setSource(rideEventPayload.getSource());
        responseModel.setDestination(rideEventPayload.getDestination());
        responseModel.setUserId(rideEventPayload.getOwnerId());
        responseModel.setSeats(rideEventPayload.getSeats());
        responseModel.setVehicleNumber(rideEventPayload.getVehicleNumber());
        responseModel.setStatus(rideEventPayload.getRideStatus());
        responseModel.setFare(rideEventPayload.getFare());
        return responseModel;
    }
}