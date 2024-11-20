package org.buildcode.rideservice.usecase.impl;

import lombok.extern.slf4j.Slf4j;
import org.buildcode.rideservice.api.model.v1_0.BookingRequestModel;
import org.buildcode.rideservice.api.model.v1_0.BookingRequestResponseModel;
import org.buildcode.rideservice.data.entity.BookingRequest;
import org.buildcode.rideservice.data.mapper.BookingRequestMapper;
import org.buildcode.rideservice.exception.RideBookRequestNotFoundException;
import org.buildcode.rideservice.repository.RideBookingRequestRepository;
import org.buildcode.rideservice.usecase.BlockchainService;
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
    private BookingRequestMapper bookingRequestMapper;

    @Autowired
    private BlockchainService blockchainService;

    @Override
    public BookingRequestResponseModel createBookingRequest(BookingRequestModel requestModel) {
        BookingRequest bookingRequest = bookingRequestMapper.toBookingRequest(requestModel);
        try {
            TransactionReceipt blockchainResponse = blockchainService.bookRideRequest();
            BookingRequest bookingRequest1 = rideBookingRequestRepository.save(bookingRequest);
            return bookingRequestMapper.toBookingRequestResponseModel(bookingRequest1);
        } catch (Exception ex) {
            log.error("Exception occurred while creating ride: {}", ex.getMessage());
            throw ex;
        }
    }

    @Override
    public BookingRequestResponseModel getBookingRequest(String bookingRequestId) {
        try {
            TransactionReceipt blockchainResponse = blockchainService.bookRideRequest();
            Optional<BookingRequest> bookingRequest = rideBookingRequestRepository.findById(bookingRequestId);
            if(bookingRequest.isEmpty()) {
                throw  new RideBookRequestNotFoundException("Ride Book Request Not Found");
            }
            return bookingRequestMapper.toBookingRequestResponseModel(bookingRequest.get());
        } catch (Exception ex) {
            log.error("Exception occurred while creating ride: {}", ex.getMessage());
            throw ex;
        }
    }
}
