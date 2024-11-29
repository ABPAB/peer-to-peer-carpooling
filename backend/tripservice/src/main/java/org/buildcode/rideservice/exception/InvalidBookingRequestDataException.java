package org.buildcode.rideservice.exception;

import lombok.Data;
import org.buildcode.rideservice.api.constants.ErrorCode;

@Data
public class InvalidBookingRequestDataException extends RideServiceException{

  public InvalidBookingRequestDataException(String message){
    super(
            ErrorCode.BOOKING_400.getCode(),
            ErrorCode.BOOKING_400.getMessage(),
            message
    );
  }
}