package org.buildcode.rideservice.api.constants;

public enum BookingRequestStatus {
    REQUESTED,

    PAYMENT_PENDING,

    BOOKED,

    REJECTED,

    CANCELLED,

    CONFIRMED,

    PAY_AFTER_RIDE_COMPLETION,

    EXPIRED
}