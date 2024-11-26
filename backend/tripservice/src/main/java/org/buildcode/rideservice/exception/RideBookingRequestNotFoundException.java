package org.buildcode.rideservice.exception;

import org.buildcode.rideservice.api.constants.ErrorCode;

public class RideBookingRequestNotFoundException extends RideServiceException {

    public RideBookingRequestNotFoundException(String details) {
        super(
                ErrorCode.BOOKING_404.getCode(),
                ErrorCode.BOOKING_404.getMessage(),
                details
        );
    }
}
