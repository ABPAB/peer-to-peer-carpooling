package org.buildcode.rideservice.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.buildcode.rideservice.api.constants.RideStatus;
import org.buildcode.rideservice.api.constants.RiderStatus;

import java.math.BigInteger;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RideNotificationPayload {
    private String riderId;
    private String ownerId;
    private String source;
    private String destination;
    private BigInteger fare;
    private String vehicleNumber;
    private String riderStatus;
}
