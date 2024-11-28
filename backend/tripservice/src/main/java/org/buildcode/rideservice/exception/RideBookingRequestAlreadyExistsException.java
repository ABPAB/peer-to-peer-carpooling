package org.buildcode.rideservice.exception;

import org.buildcode.rideservice.api.constants.ErrorCode;

public class RideBookingRequestAlreadyExistsException extends RideServiceException {
  public RideBookingRequestAlreadyExistsException(String message) {
      super(
              ErrorCode.BOOKING_REQUEST_ALREADY_EXISTS_409.getCode(),
              ErrorCode.BOOKING_REQUEST_ALREADY_EXISTS_409.getMessage(),
              message
      );
  }
}
