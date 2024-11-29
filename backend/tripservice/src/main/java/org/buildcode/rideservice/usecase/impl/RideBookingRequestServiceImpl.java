package org.buildcode.rideservice.usecase.impl;

import lombok.extern.slf4j.Slf4j;
import org.buildcode.rideservice.api.constants.BookingRequestStatus;
import org.buildcode.rideservice.api.model.v1_0.BookingRequestModel;
import org.buildcode.rideservice.api.model.v1_0.BookingRequestResponseModel;
import org.buildcode.rideservice.data.entity.BookingRequest;
import org.buildcode.rideservice.data.mapper.BookingRequestMapper;
import org.buildcode.rideservice.exception.*;
import org.buildcode.rideservice.repository.RideBookingRequestRepository;
import org.buildcode.rideservice.usecase.BlockchainService;
import org.buildcode.rideservice.usecase.NotificationService;
import org.buildcode.rideservice.usecase.RideBookingRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class RideBookingRequestServiceImpl implements RideBookingRequestService {

    @Autowired
    private RideBookingRequestRepository rideBookingRequestRepository;

    @Autowired
    private RideServiceImpl rideService;

    @Autowired
    private BookingRequestMapper bookingRequestMapper;

    @Autowired
    private BlockchainService blockchainService;

    @Autowired
    private NotificationService notificationService;
    @Autowired
    private RideServiceImpl rideServiceImpl;


    private void validateBookingRequest(String userId, String rideId) {
        if(userId == null && rideId == null) {
            throw new InvalidBookingRequestDataException("Invalid or missing value for field: userId and rideId");
        } else if(userId == null){
            throw new InvalidBookingRequestDataException("Invalid or missing value for field: userId");
        } else if(rideId == null){
            throw new InvalidBookingRequestDataException("Invalid or missing value for field: rideId");
        }

        rideServiceImpl.getRideById(rideId);
        if(rideBookingRequestRepository.existsByUserIdAndRideId(userId, rideId)) {
            throw new RideBookingRequestAlreadyExistsException("Entity already exists for userId: " + userId);
        }
    }

    @Override
    public BookingRequestResponseModel createBookingRequest(BookingRequestModel requestModel) {
        BookingRequest bookingRequest = bookingRequestMapper.toBookingRequest(requestModel);

        try {
            // TransactionReceipt blockchainResponse = blockchainService.bookRideRequest();
            String userId = requestModel.getUserId();
            String rideId = requestModel.getRideId();

            validateBookingRequest(userId, rideId);
            BookingRequest bookingRequest1 = null;
            bookingRequest1 = rideBookingRequestRepository.save(bookingRequest);
            return bookingRequestMapper.toBookingRequestResponseModel(bookingRequest1);
        } catch (Exception ex) {
            log.error("Exception occurred while creating Booking Request: {}", ex.getMessage());
            throw ex;
        }
    }

    @Override
    public BookingRequestResponseModel getBookingRequest(String bookingRequestId) {
        try {
            // TransactionReceipt blockchainResponse = blockchainService.bookRideRequest();

            Optional<BookingRequest> bookingRequest = rideBookingRequestRepository.findById(bookingRequestId);

            if (bookingRequest.isEmpty()) {
                throw new RideBookingRequestNotFoundException("Ride Book Request Not Found");
            }

            return bookingRequestMapper.toBookingRequestResponseModel(bookingRequest.get());
        } catch (Exception ex) {
            log.error("Exception occurred while fetching Booking Request details: {}", ex.getMessage());
            throw ex;
        }
    }

    @Override
    public Boolean acceptRideBookingRequest(String bookingRequestId) {
        Optional<BookingRequest> bookingRequest = rideBookingRequestRepository.findById(bookingRequestId);

        if (bookingRequest.isEmpty()) {
            throw new RideBookingRequestNotFoundException("Booking Request not found!");
        }

        BookingRequest br = bookingRequest.get();

        if (br.getStatus().equals(BookingRequestStatus.CONFIRMED)) {
            throw new RideBookingRequestCanNotBeAccepted("Can not be accepted, cause it is alredy accepted");
        }

        br.setStatus(BookingRequestStatus.CONFIRMED);
        rideBookingRequestRepository.save(br);

        log.info("Booking Request is accepted: {}", br.getBookingRequestId());

        notificationService.sendNotificationToRider(br.getUserId(), "Ride Accepted", "Your ride has been accepted!");

        return true;
    }

    @Override
    public Boolean rejectRideBookingRequest(String bookingRequestId) {
        Optional<BookingRequest> bookingRequest = rideBookingRequestRepository.findById(bookingRequestId);

        if (bookingRequest.isEmpty()) {
            throw new RideBookingRequestNotFoundException("Booking Request not found!");
        }

        BookingRequest br = bookingRequest.get();

        if (br.getStatus().equals(BookingRequestStatus.CONFIRMED)) {
            throw new RideBookingRequestCanNotBeAccepted("Can not be accepted, cause it is alredy accepted");
        }

        br.setStatus(BookingRequestStatus.REJECTED);
        rideBookingRequestRepository.save(br);

        log.info("Booking Request is rejected: {}", br.getBookingRequestId());

        notificationService.sendNotificationToRider(br.getUserId(), "Ride Rejected", "Your ride has been rejected.");

        return true;
    }
}