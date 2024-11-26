package org.buildcode.rideservice.exception;

import org.buildcode.rideservice.api.constants.ErrorCode;

public class RideBookingRequestCanNotBeAccepted extends RideServiceException {

    public RideBookingRequestCanNotBeAccepted(String details) {
        super(
                ErrorCode.BOOKING_500.getCode(),
                ErrorCode.BOOKING_500.getMessage(),
                details
        );
    }
}