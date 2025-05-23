package org.buildcode.rideservice.usecase.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.buildcode.rideservice.api.constants.ApiConstants;
import org.buildcode.rideservice.api.constants.RideStatus;
import org.buildcode.rideservice.api.model.v1_0.CreateRideRequestModel;
import org.buildcode.rideservice.api.model.v1_0.RideResponseModel;
import org.buildcode.rideservice.data.dto.RideCreatedResponsePayload;
import org.buildcode.rideservice.data.entity.Ride;
import org.buildcode.rideservice.data.mapper.RideMapper;
import org.buildcode.rideservice.exception.RideAlreadyExistsException;
import org.buildcode.rideservice.exception.RideNotFoundException;
import org.buildcode.rideservice.exception.RideValidationException;
import org.buildcode.rideservice.repository.RideRepository;
import org.buildcode.rideservice.usecase.BlockchainService;
import org.buildcode.rideservice.usecase.RideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class RideServiceImpl implements RideService {

    @Autowired
    private RideRepository rideRepository;

    @Autowired
    private RideMapper rideMapper;

    @Autowired
    private BlockchainService blockchainService;

    public void validateRide(Ride ride) {
        StringBuilder missingFields = new StringBuilder();
        if(ride.getOwnerId() == null || ride.getOwnerId().isEmpty()) {
            missingFields.append("User's id is required. ");
        }
        if(ride.getDestination() == null || ride.getDestination().isEmpty()) {
            missingFields.append("Ride destination is required. ");
        }
        if(ride.getSource() == null || ride.getSource().isEmpty()) {
            missingFields.append("Ride start location is required. ");
        }
        if(ride.getSeats() == null) {
            missingFields.append("Count of available seats required. ");
        }
        if(!missingFields.isEmpty()){
            throw new RideValidationException(missingFields.toString());
        }
        //assuming one can create only one ride at a time
        if(rideRepository.existsByOwnerIdAndAndStatus(ride.getOwnerId(), RideStatus.ACTIVE)){
            throw new RideAlreadyExistsException(ApiConstants.MESSAGE_RIDE_ALREADY_EXISTS);
        }
    }


    @Override
    public RideCreatedResponsePayload createRide(CreateRideRequestModel createRideRequestModel) {
        String uuid = generateUUID();

        Ride toBeCreated = rideMapper.toRide(createRideRequestModel, uuid);
        toBeCreated.setStatus(RideStatus.ACTIVE);

        System.out.println("toBeCreated is");
        System.out.println(toBeCreated);

        try {
            validateRide(toBeCreated);

            // add into blockchain
            Ride ride = rideRepository.save(toBeCreated);
            RideCreatedResponsePayload responsePayload = new RideCreatedResponsePayload();
            responsePayload.setRideId(ride.getId());
            responsePayload.setFare(ride.getFare());
            responsePayload.setSource(ride.getSource());
            responsePayload.setDestination(ride.getDestination());
            responsePayload.setDepartureTime(ride.getDepartureTime());
            responsePayload.setAvailableSeats(ride.getSeats());
            responsePayload.setDepartureDate(ride.getDepartureDate());
            responsePayload.setVehicleNumber(ride.getVehicleNumber());

            return blockchainService.createRide(toBeCreated);
        } catch (Exception ex) {
            log.error("Exception occurred while creating ride: {}", ex.getMessage());
            throw ex;
        }
    }

    public String generateUUID() {
        return UUID.randomUUID().toString();
    }

    @Override
    public RideResponseModel getRideById(String id) {
        try {
            Optional<Ride> ride = rideRepository.findById(id);

            if(ride.isEmpty()) {
                throw new RideNotFoundException("Ride with ID " + id + " could not be found");
            }

            return rideMapper.toRideResponseModel(ride.get());
        } catch (EntityNotFoundException entityNotFoundException) {
            throw new RideNotFoundException("Ride with ID " + id + " could not be found");
        }
    }

    @Override
    public Boolean deleteById(String rideId, String ownerId) {
        try {
            TransactionReceipt blockchainResponse = blockchainService.cancelRide(rideId, ownerId);
            rideRepository.deleteById(rideId);
        } catch (EmptyResultDataAccessException emptyResultDataAccessException) {
            throw new RideNotFoundException("Ride with ID " + rideId + " could not be found");
        }
        return true;
    }
}