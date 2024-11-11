package org.buildcode.rideservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RideServiceExceptionHandler {

    @ExceptionHandler(RideNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleRideNotFoundException(RideNotFoundException rideNotFoundException) {
        ErrorResponse errorResponse = new ErrorResponse(
                rideNotFoundException.getErrorCode(),
                rideNotFoundException.getMessage(),
                rideNotFoundException.getDetails()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}