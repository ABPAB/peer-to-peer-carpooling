package org.buildcode.rideservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.buildcode.rideservice.api.model.v1_0.BookingRequestModel;
import org.buildcode.rideservice.api.model.v1_0.BookingRequestResponseModel;
import org.buildcode.rideservice.api.resources.RideBookingRequestResource;
import org.buildcode.rideservice.data.entity.Ride;
import org.buildcode.rideservice.usecase.RideBookingRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
public class RideBookingRequestController implements RideBookingRequestResource {

    @Autowired
    private RideBookingRequestService rideBookingRequestService;

    @Override
    public ResponseEntity<BookingRequestResponseModel> createBookingRequest(BookingRequestModel requestModel) {
        BookingRequestResponseModel bookingRequestResponseModel = rideBookingRequestService.createBookingRequest(requestModel);
        return new ResponseEntity<BookingRequestResponseModel>(bookingRequestResponseModel, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<BookingRequestResponseModel> getBookingRequest(String bookingRequestId) {
        BookingRequestResponseModel bookingRequestResponseModel = rideBookingRequestService.getBookingRequest(bookingRequestId);
        return new ResponseEntity<BookingRequestResponseModel>(bookingRequestResponseModel, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Boolean> acceptRideBookingRequest(String bookingRequestId, String ownerId) {
        log.info("Got the request for accepting the ride booking request: {}", bookingRequestId);
        Boolean result = rideBookingRequestService.acceptRideBookingRequest(bookingRequestId, ownerId);
        log.info("Ride Request status: {}", result);
        return new ResponseEntity<Boolean>(result, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Boolean> rejectRideBookingRequest(String bookingRequestId) {
        log.info("Got the request for rejecting the ride booking request: {}", bookingRequestId);
        Boolean result = rideBookingRequestService.rejectRideBookingRequest(bookingRequestId);
        log.info("Ride Rejected successfully!");
        return new ResponseEntity<Boolean>(result, HttpStatus.OK);
    }
}