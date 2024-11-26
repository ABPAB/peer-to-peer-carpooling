package org.buildcode.rideservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.buildcode.rideservice.api.model.v1_0.CreateRideRequestModel;
import org.buildcode.rideservice.api.model.v1_0.RideResponseModel;
import org.buildcode.rideservice.api.resources.RideResource;
import org.buildcode.rideservice.data.entity.Ride;
import org.buildcode.rideservice.usecase.RideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;


@Controller
@Slf4j
public class RideController implements RideResource {

    @Autowired
    private RideService rideService;


    @Override
    public ResponseEntity<Ride> createRideRequest(CreateRideRequestModel createRideRequestModel) {
        log.info("Got the request for create ride: {}", createRideRequestModel);
        Ride ride = rideService.createRide(createRideRequestModel);
        log.info("Created the ride: {}", ride);
        return new ResponseEntity<Ride>(ride, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<RideResponseModel> getRideRequest(String id) {
        log.info("Got the request for details of ride: {}", id);
        RideResponseModel ride = rideService.getRideById(id);
        log.info("Got the ride for id: {} , {}", id, ride);
        return new ResponseEntity<RideResponseModel>(ride, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Boolean> deleteRideRequest(String id) {
        log.info("Got the request for delete ride for id: {}", id);
        Boolean result = rideService.deleteById(id);
        log.info("Deleted the ride of id: {} ", id);
        return new ResponseEntity<Boolean>(result, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Ride> updateRideRequest(String id, CreateRideRequestModel createRideRequestMode) {
        return null;
    }
}