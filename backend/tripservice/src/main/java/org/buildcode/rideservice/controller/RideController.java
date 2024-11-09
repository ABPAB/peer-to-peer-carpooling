package org.buildcode.rideservice.controller;

import org.buildcode.rideservice.api.constants.RideStatus;
import org.buildcode.rideservice.api.resources.RideResource;
import org.buildcode.rideservice.data.entity.Ride;
import org.buildcode.rideservice.repository.RideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.time.Instant;

@Controller
public class RideController implements RideResource {

    @Autowired
    RideRepository rideRepository;


    @Override
    public ResponseEntity<Ride> getBookingRequest(String id) {
        // Testing
        Ride newRide = new Ride();
        newRide.setSource("source");
        newRide.setDestination("destination");
        newRide.setUserId("userId");
        newRide.setStatus(RideStatus.CREATED);
        newRide.setCarModel("carModel");
        newRide.setSeats(2);
        newRide.setSource("source");
        newRide.setSource("source");

        Ride savedRecords = rideRepository.save(newRide);

        return new ResponseEntity<Ride>(savedRecords, HttpStatus.CREATED);
    }
}