package org.buildcode.synchservice.data.mapper;

import org.buildcode.synchservice.api.constants.RideStatus;
import org.buildcode.synchservice.data.entity.Ride;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.time.Instant;

@Component
public class RideMapper {

    public Ride toRideUpdateKafkaPayload(JSONObject jsonPayload) {
        Ride newRide = new Ride();

        if (jsonPayload.has("rideId") && !jsonPayload.isNull("rideId")) {
            newRide.setId(jsonPayload.getString("rideId"));
        }

        if (jsonPayload.has("ownerId") && !jsonPayload.isNull("ownerId")) {
            newRide.setUserId(jsonPayload.getString("ownerId"));
        }

        if (jsonPayload.has("source") && !jsonPayload.isNull("source")) {
            newRide.setSource(jsonPayload.getString("source"));
        }

        if (jsonPayload.has("destination") && !jsonPayload.isNull("destination")) {
            newRide.setDestination(jsonPayload.getString("destination"));
        }

        if (jsonPayload.has("fare") && !jsonPayload.isNull("fare")) {
            newRide.setFare(BigInteger.valueOf(jsonPayload.getLong("fare")));
        }

        if (jsonPayload.has("seats") && !jsonPayload.isNull("seats")) {
            newRide.setSeats(BigInteger.valueOf(jsonPayload.getInt("seats")));
        }

        if (jsonPayload.has("vehicleNumber") && !jsonPayload.isNull("vehicleNumber")) {
            newRide.setVehicleNumber(jsonPayload.getString("vehicleNumber"));
        }

        if (jsonPayload.has("rideStatus") && !jsonPayload.isNull("rideStatus")) {
            newRide.setStatus(RideStatus.valueOf(jsonPayload.getString("rideStatus")));
        }

        if (jsonPayload.has("updatedAt") && !jsonPayload.isNull("updatedAt")) {
            newRide.setUpdatedAt(Instant.parse(jsonPayload.getString("updatedAt")));
        } else {
            newRide.setUpdatedAt(Instant.now());
        }

        return newRide;
    }
}