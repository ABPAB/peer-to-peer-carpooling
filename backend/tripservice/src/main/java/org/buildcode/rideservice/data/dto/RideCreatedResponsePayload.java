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
public class RideCreatedResponsePayload {

    private String rideId;
    private String source;
    private String destination;
    private BigInteger fare;
    private BigInteger availableSeats;
    private String vehicleNumber;
    private String departureTime;
    private String departureDate;
}
