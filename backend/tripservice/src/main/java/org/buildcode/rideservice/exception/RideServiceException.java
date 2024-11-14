package org.buildcode.rideservice.exception;


public class RideServiceException extends RuntimeException {
    private String  errorCode;
    private String details;

    public RideServiceException(String errorCode, String message, String details) {
        super(message);
        this.errorCode = errorCode;
        this.details = details;
    }

    public String getDetails() {
        return details;
    }

    public String getErrorCode() {
        return errorCode;
    }
}