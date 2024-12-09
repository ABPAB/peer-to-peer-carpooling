package org.buildcode.rideservice.data.mapper;

import lombok.extern.slf4j.Slf4j;
import org.buildcode.rideservice.api.model.v1_0.CreateRideRequestModel;
import org.buildcode.rideservice.api.model.v1_0.RideResponseModel;
import org.buildcode.rideservice.data.dto.RideEventPayload;
import org.buildcode.rideservice.data.entity.Ride;
import org.buildcode.rideservice.security.UserInfoContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RideMapper {

    @Autowired
    private UserInfoContext userInfoContext;

    public Ride toRide(CreateRideRequestModel createRideRequestModel, String uuid) {
        Ride ride = new Ride();

        log.info("Creating ride object");
//        log.info("user context: {}", userInfoContext.getUserDetails());
//        log.info("user context: {}", userInfoContext.getUserDetails().getUid());

        ride.setId(uuid);
        ride.setOwnerId(uuid);
        ride.setSource(createRideRequestModel.getSource());
        ride.setStatus(createRideRequestModel.getStatus());
        ride.setDestination(createRideRequestModel.getDestination());
        ride.setSeats(createRideRequestModel.getSeats());
        ride.setVehicleNumber(createRideRequestModel.getCarModel());
        ride.setFare(createRideRequestModel.getFare());
        ride.setDepartureTime(String.valueOf(createRideRequestModel.getDepartureTime()));
        ride.setDepartureDate(String.valueOf(createRideRequestModel.getDepartureDate()));

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