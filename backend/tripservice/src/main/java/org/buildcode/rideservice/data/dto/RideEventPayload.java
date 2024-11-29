package org.buildcode.rideservice.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.buildcode.rideservice.api.constants.RideStatus;

import java.math.BigInteger;
import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RideEventPayload {
    private String rideId;
    private String ownerId;
    private String source;
    private String destination;
    private BigInteger fare;
    private BigInteger seats;
    private String vehicleNumber;
    private RideStatus rideStatus;
    private BigInteger updatedAt;
}
