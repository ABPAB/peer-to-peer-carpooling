package org.buildcode.rideservice.exception;

import org.buildcode.rideservice.api.constants.ErrorCode;

public class RideValidationException extends RideServiceException {
    public RideValidationException(String details) {
      super(
              ErrorCode.RIDE_400.getCode(),
              ErrorCode.RIDE_400.getMessage(),
              details
      );
    }
}
