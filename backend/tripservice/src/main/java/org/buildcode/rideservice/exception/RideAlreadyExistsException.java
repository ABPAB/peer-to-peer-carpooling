package org.buildcode.rideservice.exception;

import org.buildcode.rideservice.api.constants.ErrorCode;

public class RideAlreadyExistsException extends RideServiceException {
    public RideAlreadyExistsException(String message) {
        super(
                ErrorCode.RIDE_ALREADY_EXISTS_409.getCode(),
                ErrorCode.RIDE_ALREADY_EXISTS_409.getMessage(),
                message
        );
    }
}
