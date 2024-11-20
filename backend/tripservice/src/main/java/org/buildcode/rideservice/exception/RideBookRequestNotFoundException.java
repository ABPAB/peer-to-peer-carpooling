package org.buildcode.rideservice.exception;

import org.buildcode.rideservice.api.constants.ErrorCode;

public class RideBookRequestNotFoundException extends RideServiceException {
    public RideBookRequestNotFoundException(String details) {
        super(
                ErrorCode.RIDE_404.getCode(),
                ErrorCode.RIDE_404.getMessage(),
                details
        );
    }
}
