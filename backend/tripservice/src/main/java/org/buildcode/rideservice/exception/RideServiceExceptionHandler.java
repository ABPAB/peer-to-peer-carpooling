package org.buildcode.rideservice.exception;

import com.fasterxml.jackson.databind.JsonMappingException;
import org.buildcode.rideservice.api.constants.ErrorCode;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

    @ExceptionHandler(RideBookingRequestAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleRideBookingRequestAlreadyExistsException(RideBookingRequestAlreadyExistsException rideBookingRequestAlreadyExistsException) {
        ErrorResponse errorResponse = new ErrorResponse(
                rideBookingRequestAlreadyExistsException.getErrorCode(),
                rideBookingRequestAlreadyExistsException.getMessage(),
                rideBookingRequestAlreadyExistsException.getDetails()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(RideBookingRequestNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleRideBookingRequestNotFoundException(RideBookingRequestNotFoundException rideBookingRequestNotFoundException) {
        ErrorResponse errorResponse = new ErrorResponse(
                rideBookingRequestNotFoundException.getErrorCode(),
                rideBookingRequestNotFoundException.getMessage(),
                rideBookingRequestNotFoundException.getDetails()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ InvalidBookingRequestDataException.class })
    public ResponseEntity<ErrorResponse> handleInvalidBookingRequestDataException(InvalidBookingRequestDataException exception){
        ErrorResponse errorResponse = new ErrorResponse(
                exception.getErrorCode(),
                exception.getMessage(),
                exception.getDetails()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RideBookingRequestCanNotBeAccepted.class)
    public ResponseEntity<ErrorResponse> handleRideBookingRequestCanNotBeAccepted(RideBookingRequestCanNotBeAccepted exception){
        ErrorResponse errorResponse = new ErrorResponse(
                exception.getErrorCode(),
                exception.getMessage(),
                exception.getDetails()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(RideValidationException.class)
    public ResponseEntity<ErrorResponse> handleRideValidationException(RideValidationException exception){
        ErrorResponse errorResponse = new ErrorResponse(
                exception.getErrorCode(),
                exception.getMessage(),
                exception.getDetails()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RideAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleRideAlreadyExistsException(RideAlreadyExistsException exception){
        ErrorResponse errorResponse = new ErrorResponse(
                exception.getErrorCode(),
                exception.getMessage(),
                exception.getDetails()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }
}