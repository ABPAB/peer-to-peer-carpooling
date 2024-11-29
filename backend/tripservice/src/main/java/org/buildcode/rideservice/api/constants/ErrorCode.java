package org.buildcode.rideservice.api.constants;

public enum ErrorCode {
    // Ride-related errors
    RIDE_404("RIDE_404", "Ride not found"),
    RIDE_400("RIDE_400", "Invalid ride request"),

    // Booking-related errors
    SUCCESS_FOR_BOOKING_REQUEST_201("SUCCESS_FOR_BOOKING_REQUEST_201", ApiConstants.MESSAGE_SUCCESS_FOR_BOOKING_REQUEST),
    BOOKING_REQUEST_ALREADY_EXISTS_409("BOOKING_REQUEST_ALREADY_EXISTS_409",ApiConstants.MESSAGE_BOOKING_REQUEST_ALREADY_EXISTS),
    BOOKING_404("BOOKING_404", "Booking not found"),
    BOOKING_500("BOOKING_500", "Booking can not be accepted"),
    BOOKING_400("BOOKING_400", "Invalid booking request"),

    // Payment-related errors
    PAYMENT_500("PAYMENT_500", "Payment processing error"),
    PAYMENT_402("PAYMENT_402", "Payment required"),

    // Customer-related errors
    CUSTOMER_404("CUSTOMER_404", "Customer not found"),
    CUSTOMER_400("CUSTOMER_400", "Invalid customer details"),

    // General errors
    INTERNAL_SERVER_ERROR("INTERNAL_SERVER_ERROR", "An unexpected error occurred"),
    BAD_REQUEST("BAD_REQUEST", "Invalid request");

    private final String code;
    private final String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}