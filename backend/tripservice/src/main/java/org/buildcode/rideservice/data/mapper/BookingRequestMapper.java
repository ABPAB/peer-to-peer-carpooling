package org.buildcode.rideservice.data.mapper;

import org.buildcode.rideservice.api.constants.BookingRequestStatus;
import org.buildcode.rideservice.api.model.v1_0.BookingRequestModel;
import org.buildcode.rideservice.api.model.v1_0.BookingRequestResponseModel;
import org.buildcode.rideservice.data.entity.BookingRequest;
import org.springframework.stereotype.Component;

@Component
public class BookingRequestMapper {

    public BookingRequestResponseModel toBookingRequestResponseModel(BookingRequest bookingRequest) {
        BookingRequestResponseModel bookingRequestResponseModel = new BookingRequestResponseModel();
        bookingRequestResponseModel.setRideId(bookingRequest.getRideId());
        bookingRequestResponseModel.setStatus(bookingRequest.getStatus());
        bookingRequestResponseModel.setCreatedAt(bookingRequest.getCreatedAt());
        bookingRequestResponseModel.setUpdatedAt(bookingRequest.getUpdatedAt());
        bookingRequestResponseModel.setRiderId(bookingRequest.getRiderId());

        return bookingRequestResponseModel;
    }

    public BookingRequest toBookingRequest(BookingRequestModel bookingRequestModel) {
        BookingRequest bookingRequest = new BookingRequest();
        bookingRequest.setRideId(bookingRequestModel.getRideId());
        bookingRequest.setStatus(BookingRequestStatus.REQUESTED);
        bookingRequest.setRiderId(bookingRequestModel.getRiderId());
        return bookingRequest;
    }
}
