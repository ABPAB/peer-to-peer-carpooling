package org.buildcode.rideservice.exception;


public class ErrorResponse {
    private String errorCode;
    private String message;
    private String details;

    public ErrorResponse(String errorCode, String message, String details) {
        this.errorCode = errorCode;
        this.message = message;
        this.details = details;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }
}