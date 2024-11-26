package org.buildcode.rideservice.usecase.impl;

import lombok.extern.slf4j.Slf4j;
import org.buildcode.rideservice.data.entity.Ride;
import org.buildcode.rideservice.repository.RideRepository;
import org.buildcode.rideservice.usecase.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private RideRepository rideRepository;


    public void sendNotificationToRider(String rideId, String title, String message) {
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