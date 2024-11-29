package org.buildcode.rideservice.usecase;

import org.buildcode.rideservice.api.model.v1_0.BookingRequestModel;
import org.buildcode.rideservice.api.model.v1_0.BookingRequestResponseModel;

public interface RideBookingRequestService {

    BookingRequestResponseModel createBookingRequest(BookingRequestModel requestModel);

    BookingRequestResponseModel getBookingRequest(String bookingRequestId);

    Boolean acceptRideBookingRequest(String bookingRequestId, String ownerId);

    Boolean rejectRideBookingRequest(String bookingRequestId);
}