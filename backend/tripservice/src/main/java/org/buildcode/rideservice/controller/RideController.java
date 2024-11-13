package org.buildcode.rideservice.controller;

import org.buildcode.rideservice.api.constants.RideStatus;
import org.buildcode.rideservice.api.resources.RideResource;
import org.buildcode.rideservice.data.entity.Ride;
import org.buildcode.rideservice.repository.RideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

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

    // Accept ride request
    public ResponseEntity<String> acceptRide(String rideId, String riderId) {
        // Find the ride and update its status
        Ride ride = rideRepository.findById(rideId).orElse(null);
        if (ride != null) {
            ride.setStatus(RideStatus.ACCEPTED);
            rideRepository.save(ride);

            // Send notification to the rider
            sendNotificationToRider(riderId, "Ride Accepted", "Your ride has been accepted!");

            return new ResponseEntity<>("Ride accepted", HttpStatus.OK);
        }
        return new ResponseEntity<>("Ride not found", HttpStatus.NOT_FOUND);
    }

    // Reject ride request
    public ResponseEntity<String> rejectRide(String rideId, String riderId) {
        Ride ride = rideRepository.findById(rideId).orElse(null);
        if (ride != null) {
            ride.setStatus(RideStatus.REJECTED);
            rideRepository.save(ride);

            // Send notification to the rider
            sendNotificationToRider(riderId, "Ride Rejected", "Your ride has been rejected.");

            return new ResponseEntity<>("Ride rejected", HttpStatus.OK);
        }
        return new ResponseEntity<>("Ride not found", HttpStatus.NOT_FOUND);
    }

    // Cancel ride
    public ResponseEntity<String> cancelRide(String rideId, String riderId) {
        Ride ride = rideRepository.findById(rideId).orElse(null);
        if (ride != null) {
            ride.setStatus(RideStatus.CANCELED);
            rideRepository.save(ride);

            // Send notification to the rider
            sendNotificationToRider(riderId, "Ride Canceled", "Your ride has been canceled.");

            return new ResponseEntity<>("Ride canceled", HttpStatus.OK);
        }
        return new ResponseEntity<>("Ride not found", HttpStatus.NOT_FOUND);
    }

    // Send notification to the rider
    private void sendNotificationToRider(String riderId, String title, String message) {
        String notificationServiceUrl = "http://localhost:5000/send-notification";
        RestTemplate restTemplate = new RestTemplate();

        // Create payload for the notification
        String payload = "{ \"riderId\": \"" + riderId + "\", \"title\": \"" + title + "\", \"message\": \"" + message + "\" }";

        // Send the request to the notification service
        restTemplate.postForObject(notificationServiceUrl, payload, String.class);
    }
}