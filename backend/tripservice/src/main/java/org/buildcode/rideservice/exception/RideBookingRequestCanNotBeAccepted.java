package org.buildcode.rideservice.exception;

import org.buildcode.rideservice.api.constants.ErrorCode;

public class RideBookingRequestCanNotBeAccepted extends RideServiceException {

    public RideBookingRequestCanNotBeAccepted(String details) {
        super(
                ErrorCode.BOOKING_CONFLICT_409.getCode(),
                ErrorCode.BOOKING_CONFLICT_409.getMessage(),
                details
        );
    }
}