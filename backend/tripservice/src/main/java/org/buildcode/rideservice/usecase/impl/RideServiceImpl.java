package org.buildcode.rideservice.usecase.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.buildcode.rideservice.api.constants.RideStatus;
import org.buildcode.rideservice.api.model.v1_0.CreateRideRequestModel;
import org.buildcode.rideservice.api.model.v1_0.RideResponseModel;
import org.buildcode.rideservice.data.entity.Ride;
import org.buildcode.rideservice.data.mapper.RideMapper;
import org.buildcode.rideservice.exception.RideNotFoundException;
import org.buildcode.rideservice.repository.RideRepository;
import org.buildcode.rideservice.usecase.BlockchainService;
import org.buildcode.rideservice.usecase.RideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

import java.util.Optional;

@Service
@Slf4j
public class RideServiceImpl implements RideService {

    @Autowired
    private RideRepository rideRepository;

    @Autowired
    private RideMapper rideMapper;

    @Autowired
    private BlockchainService blockchainService;


    @Override
    public Ride createRide(CreateRideRequestModel createRideRequestModel) {
        Ride newRide = rideMapper.toRide(createRideRequestModel);

        try {
            // add into blockchain
            TransactionReceipt blockchainResponse = blockchainService.createRide(newRide);

            // assign the initial status
            newRide.setStatus(RideStatus.ACTIVE);
            return rideRepository.save(newRide);
        } catch (Exception ex) {
            log.error("Exception occurred while creating ride: {}", ex.getMessage());
            throw ex;
        }
    }

    @Override
    public RideResponseModel getRideById(String id) {
        try {
            Optional<Ride> ride = rideRepository.findById(id);

            if(ride.isEmpty()) {
                throw new RideNotFoundException("Ride couldn't found");
            }

            return rideMapper.toRideResponseModel(ride.get());
        } catch (EntityNotFoundException entityNotFoundException) {
            throw new RideNotFoundException("Ride with ID " + id + " could not be found");
        }
    }

    @Override
    public Boolean deleteById(String id) {
        try {
            rideRepository.deleteById(id);
        } catch (EmptyResultDataAccessException emptyResultDataAccessException) {
            throw new RideNotFoundException("Ride with ID " + id + " could not be found");
        }
        return true;
    }
}