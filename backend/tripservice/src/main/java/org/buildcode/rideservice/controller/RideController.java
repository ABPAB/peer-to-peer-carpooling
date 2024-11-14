package org.buildcode.rideservice.controller;

import org.buildcode.rideservice.api.constants.RideStatus;
import org.buildcode.rideservice.api.resources.RideResource;
import org.buildcode.rideservice.data.entity.Ride;
import org.buildcode.rideservice.repository.RideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.Map;
import java.util.HashMap;

import java.time.Instant;
import java.util.HashMap;

@Controller
public class RideController implements RideResource {

    @Autowired
    RideRepository rideRepository;

     // Endpoint to update the device token for a rider
    @PostMapping("/{rideId}/set-device-token")
    public ResponseEntity<String> setDeviceToken(@PathVariable String rideId, @RequestBody String deviceToken) {
        Ride ride = rideRepository.findById(rideId).orElse(null);
        if (ride == null) {
            return new ResponseEntity<>("Ride not found", HttpStatus.NOT_FOUND);
        }

        ride.setDeviceToken(deviceToken);  // Store the device token
        rideRepository.save(ride);  // Save the updated ride data
        return new ResponseEntity<>("Device token saved successfully", HttpStatus.OK);
    }

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

    @Override
    public ResponseEntity<String> acceptRide(String id) {
        // Accept ride logic
        Ride ride = rideRepository.findById(id).orElse(null);
        if (ride != null) {
            ride.setStatus(RideStatus.ACCEPTED);
            rideRepository.save(ride);
            // Send notification to the rider
            sendNotificationToRider(ride.getUserId(), "Ride Accepted", "Your ride has been accepted!");
            return new ResponseEntity<>("Ride accepted", HttpStatus.OK);
        }
        return new ResponseEntity<>("Ride not found", HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<String> rejectRide(String id) {
        // Reject ride logic
        Ride ride = rideRepository.findById(id).orElse(null);
        if (ride != null) {
            ride.setStatus(RideStatus.REJECTED);
            rideRepository.save(ride);
            // Send notification to the rider
            sendNotificationToRider(ride.getUserId(), "Ride Rejected", "Your ride has been rejected.");
            return new ResponseEntity<>("Ride rejected", HttpStatus.OK);
        }
        return new ResponseEntity<>("Ride not found", HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<String> cancelRide(String id) {
        // Cancel ride logic
        Ride ride = rideRepository.findById(id).orElse(null);
        if (ride != null) {
            ride.setStatus(RideStatus.CANCELED);
            rideRepository.save(ride);
             // Send notification to the rider
             sendNotificationToRider(ride.getUserId(), "Ride Canceled", "Your ride has been canceled.");
            return new ResponseEntity<>("Ride canceled", HttpStatus.OK);
        }
        return new ResponseEntity<>("Ride not found", HttpStatus.NOT_FOUND);
    }

    // // Send notification to the rider
    // private void sendNotificationToRider(String riderId, String title, String message) {
    //     String notificationServiceUrl = "http://localhost:5001/send-notification";
    //     RestTemplate restTemplate = new RestTemplate();

    //     // Create payload as a Map
    //     Map<String, String> payload = new HashMap<>();
    //     payload.put("riderId", riderId);
    //     payload.put("title", title);
    //     payload.put("message", message);

    //     // Set headers
    //     HttpHeaders headers = new HttpHeaders();
    //     headers.setContentType(MediaType.APPLICATION_JSON);

    //     // Create the HttpEntity object to include payload and headers
    //     HttpEntity<Map<String, String>> request = new HttpEntity<>(payload, headers);

    //     // Send the request to the notification service
    //     try {
    //         restTemplate.postForObject(notificationServiceUrl, request, String.class);
    //     } catch (Exception e) {
    //         System.err.println("Error sending notification: " + e.getMessage());
    //     }
    // }

    private void sendNotificationToRider(String rideId, String title, String message) {
        Ride ride = rideRepository.findById(rideId).orElse(null);
        if (ride != null && ride.getDeviceToken() != null) {
            String token = ride.getDeviceToken();
            String notificationServiceUrl = "http://localhost:5002/send-notification";
            RestTemplate restTemplate = new RestTemplate();
    
            // Create payload as a Map
            Map<String, String> payload = new HashMap<>();
            payload.put("token", token);  // Send the device token here
            payload.put("title", title);
            payload.put("message", message);
    
            // Set headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
    
            // Create the HttpEntity object to include payload and headers
            HttpEntity<Map<String, String>> request = new HttpEntity<>(payload, headers);
    
            // Send the request to the notification service
            try {
                restTemplate.postForObject(notificationServiceUrl, request, String.class);
            } catch (Exception e) {
                System.err.println("Error sending notification: " + e.getMessage());
            }
        }
    }
    
}


    @Override
    public ResponseEntity<String> requestRide(String rideId) {
    // Find the ride the rider is requesting
    Ride ride = rideRepository.findById(rideId).orElse(null);
    if (ride != null) {
        // Check if the ride is available for booking (you can add conditions)
        if (ride.getStatus().equals(RideStatus.CREATED)) {
            // Update the ride to show that the rider has requested it
            ride.setStatus(RideStatus.REQUESTED);  // Or keep it 'CREATED' if you want
            rideRepository.save(ride);

            // Optional: Notify the car owner about the ride request (You can add this notification later)
            
            return new ResponseEntity<>("Ride request successfully made", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Ride is not available for request", HttpStatus.BAD_REQUEST);
        }
    }
    return new ResponseEntity<>("Ride not found", HttpStatus.NOT_FOUND);
}


}