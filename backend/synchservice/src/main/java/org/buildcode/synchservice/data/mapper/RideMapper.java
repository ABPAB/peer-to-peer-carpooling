package org.buildcode.synchservice.data.mapper;

import org.buildcode.synchservice.api.constants.RideStatus;
import org.buildcode.synchservice.data.entity.Ride;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.time.Instant;
import java.util.Optional;

@Component
public class RideMapper {

    public Ride toRideUpdateKafkaPayload(JSONObject jsonPayload) {
        Ride newRide = new Ride();

        System.out.println("Processing JSON Payload: " + jsonPayload.toString());

        Optional.ofNullable(jsonPayload.optString("id", null)).ifPresent(newRide::setId);
        Optional.ofNullable(jsonPayload.optString("ownerId", null)).ifPresent(newRide::setOwnerId);
        Optional.ofNullable(jsonPayload.optString("source", null)).ifPresent(newRide::setSource);
        Optional.ofNullable(jsonPayload.optString("destination", null)).ifPresent(newRide::setDestination);
        Optional.ofNullable(jsonPayload.optString("deviceToken", null)).ifPresent(newRide::setDeviceToken);
        Optional.ofNullable(jsonPayload.optString("vehicleNumber", null)).ifPresent(newRide::setVehicleNumber);
        Optional.ofNullable(jsonPayload.optString("departureTime", null)).ifPresent(newRide::setDepartureTime);
        Optional.ofNullable(jsonPayload.optString("departureDate", null)).ifPresent(newRide::setDepartureDate);

        if (jsonPayload.has("fare") && !jsonPayload.isNull("fare")) {
            newRide.setFare(BigInteger.valueOf(jsonPayload.getLong("fare")));
        }

        if (jsonPayload.has("seats") && !jsonPayload.isNull("seats")) {
            newRide.setSeats(BigInteger.valueOf(jsonPayload.getInt("seats")));
        }

        if (jsonPayload.has("status") && !jsonPayload.isNull("status")) {
            newRide.setStatus(RideStatus.valueOf(jsonPayload.getString("status")));
        }

        if (jsonPayload.has("createdAt") && !jsonPayload.isNull("createdAt")) {
            newRide.setCreatedAt(Instant.parse(jsonPayload.getString("createdAt")));
        } else {
            newRide.setCreatedAt(Instant.now());
        }

        if (jsonPayload.has("updatedAt") && !jsonPayload.isNull("updatedAt")) {
            newRide.setUpdatedAt(Instant.parse(jsonPayload.getString("updatedAt")));
        } else {
            newRide.setUpdatedAt(Instant.now());
        }

        return newRide;
    }
}