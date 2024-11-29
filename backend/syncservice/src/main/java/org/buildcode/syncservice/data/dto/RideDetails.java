package org.buildcode.syncservice.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.buildcode.syncservice.api.constants.RideStatus;

import java.math.BigInteger;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RideDetails {
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