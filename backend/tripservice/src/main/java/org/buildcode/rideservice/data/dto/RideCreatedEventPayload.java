package org.buildcode.rideservice.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RideCreatedEventPayload {
    private String rideId;
    private String ownerId;
    private String source;
    private String destination;
    private BigInteger fare;
    private BigInteger seats;
    private String vehicleNumber;
}
