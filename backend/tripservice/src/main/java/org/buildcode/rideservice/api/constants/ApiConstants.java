package org.buildcode.rideservice.api.constants;

public class ApiConstants {
    // BLOCKCHAIN
    public final static String BLOCKCHAIN_BASE_URL_V1 = "http://localhost:8545";

    // API URI
    public final static String BOOKING_SERVICE_V1 = "/trip-service/v1";
    public final static String BOOKING = "/booking";
    public final static String BOOKING_REQUEST = "/request";
    public final static String RIDE_DEVICE = "/device";

    //RIDEs
    public final static String MESSAGE_CREATED_FOR_RIDE = "Ride created successfully";
    public final static String MESSAGE_INVALID_RIDE = "Invalid ride request";
    public final static String MESSAGE_RIDE_NOT_FOUND = "Ride not found";
    public final static String MESSAGE_RIDE_ALREADY_EXISTS = "You have already created a ride and cannot make another request until the current one is resolved";


    public final static String MESSAGE_SUCCESS_FOR_BOOKING_REQUEST = "Booking request created successfully";
    public final static String ACCESS_DENIED_FOR_OWNER_ACCESS_TO_BOOKING_REQUEST = "Access Denied. You do not have permission to create a booking request.";
    public final static String MESSAGE_BOOKING_REQUEST_ALREADY_EXISTS = "You have already requested a ride and cannot make another request until the current one is resolved";
    public final static String RIDE_REQUESTED_NOT_FOUND = "Ride Not Found. The requested ride cannot be located.";
    public final static String MESSAGE_BOOKING_REQUEST_RETRIEVED_SUCCESSFULLY = "Booking request details retrieved successfully";
    public final static String MESSAGE_INVALID_BOOKING_REQUEST_ID = "The booking request ID provided is invalid or missing. Please verify the ID and try again.";
    public final static String MESSAGE_BOOKING_REQUEST_NOT_FOUND = "The booking request with ID was not found. Please verify the ID and try again.";

    //Accepting request
    public final static String MESSAGE_BOOKING_ACCEPTED = "Your booking request has been successfully accepted. We are processing your details.";
    public final static String MESSAGE_BOOKING_REQUEST_GONE = "The booking request is no longer available. It may have been canceled or expired.";
    public final static String MESSAGE_ACCESS_DENIED_FOR_RIDER_TO_ACCEPT_BOOKING_REQUEST = "You do not have permission to accept booking request. Please check your account or role.";
    public final static String MESSAGE_BOOKING_REQUEST_TO_ACCEPT_NOT_FOUND= "The booking request you're trying to accept could not be found. Please check the details.";
    public final static String MESSAGE_BOOKING_CONFLICT = "The booking request cannot be accepted due to a conflict with existing bookings.";

    //rejecting request
    public final static String MESSAGE_BOOKING_REQUEST_REJECTED = "The booking request has been successfully rejected.";
    public final static String MESSAGE_ACCESS_DENIED_FOR_RIDER_TO_REJECT_BOOKING_REQUEST = "You do not have permission to reject booking request. Please check your account or role.";
    public final static String MESSAGE_BOOKING_REQUEST_TO_REJECT_NOT_FOUND= "The rejection request is invalid. Please check the details and try again.";

    // API error code
    public final static String INTERNAL_SERVER_ERROR = "INTERNAL_SERVER_ERROR";
    public final static String UNAUTHORIZED = "UNAUTHORIZED";
    public final static String BAD_REQUEST = "BAD_REQUEST";
    public final static String NOT_FOUND = "NOT_FOUND";
    public final static String INVALID_BOOKING_REQUEST_DATA = "MESSAGE_INVALID_BOOKING_REQUEST_DATA";
    public final static String ROOM_UNAVAILABLE = "ROOM_UNAVAILABLE";

    public final static String MESSAGE_SUCCESS = "Success";
    public final static String MESSAGE_BAD_REQUEST = "Bad Request";
    public final static String MESSAGE_UNAUTHORIZED = "Unauthorized";
    public final static String MESSAGE_INVALID_BOOKING_REQUEST_DATA = "Invalid booking request data";
    public final static String MESSAGE_INTERNAL_SERVER_ERROR = "Internal Server Error";
    public final static String MESSAGE_NOT_FOUND = "Not found";
    public final static String MESSAGE_SERVICE_UNAVAILABLE = "This service is currently unavailable";
}