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
import org.web3j.protocol.core.methods.response.TransactionReceipt;

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
        if(rideBookingRequestRepository.existsByRiderIdAndRideId(userId, rideId)) {
            throw new RideBookingRequestAlreadyExistsException("Entity already exists for userId: " + userId);
        }
    }

    @Override
    public BookingRequestResponseModel createBookingRequest(BookingRequestModel requestModel) {
        BookingRequest bookingRequest = bookingRequestMapper.toBookingRequest(requestModel);

        try {
            String userId = requestModel.getRiderId();
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
            Optional<BookingRequest> bookingRequest = rideBookingRequestRepository.findById(bookingRequestId);

            if (bookingRequest.isEmpty()) {
                throw new RideBookingRequestNotFoundException("Ride Book request with id: "+bookingRequestId+" not Found");
            }

            return bookingRequestMapper.toBookingRequestResponseModel(bookingRequest.get());
        } catch (Exception ex) {
            log.error("Exception occurred while fetching Booking Request details: {}", ex.getMessage());
            throw ex;
        }
    }

    @Override
    public Boolean acceptRideBookingRequest(String bookingRequestId, String ownerId) {
        Optional<BookingRequest> bookingRequest = rideBookingRequestRepository.findById(bookingRequestId);

        BookingRequest br = bookingRequest.orElseThrow(() ->
                new RideBookingRequestNotFoundException("Ride booking request with id: "+bookingRequestId+" not Found")
        );
        TransactionReceipt blockchainResponse = blockchainService.acceptRideRequest(br.getRideId(), ownerId, br.getRiderId());

        if (br.getStatus().equals(BookingRequestStatus.CONFIRMED)) {
            throw new RideBookingRequestCanNotBeAccepted("Ride booking request already accepted");
        }

        br.setStatus(BookingRequestStatus.CONFIRMED);
        rideBookingRequestRepository.save(br);

        log.info("Booking Request is accepted: {}", br.getBookingRequestId());

        notificationService.sendNotificationToRider(br.getRiderId(), "Ride Accepted", "Your ride has been accepted!");

        return true;
    }

    @Override
    public Boolean rejectRideBookingRequest(String bookingRequestId) {
        Optional<BookingRequest> bookingRequest = rideBookingRequestRepository.findById(bookingRequestId);

        if (bookingRequest.isEmpty()) {
            throw new RideBookingRequestNotFoundException("Ride booking request with id:" +bookingRequestId+ " not Found");
        }

        BookingRequest br = bookingRequest.get();

        //is this correct
        if (br.getStatus().equals(BookingRequestStatus.CONFIRMED)) {
            throw new RideBookingRequestCanNotBeAccepted("Ride booking request already rejected");
        }

        br.setStatus(BookingRequestStatus.REJECTED);
        rideBookingRequestRepository.save(br);

        log.info("Booking Request is rejected: {}", br.getBookingRequestId());

        notificationService.sendNotificationToRider(br.getRiderId(), "Ride Rejected", "Your ride has been rejected.");

        return true;
    }
}