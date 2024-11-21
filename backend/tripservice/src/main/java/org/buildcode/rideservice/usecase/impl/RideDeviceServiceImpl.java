package org.buildcode.rideservice.usecase.impl;

import lombok.extern.slf4j.Slf4j;
import org.buildcode.rideservice.api.model.v1_0.RideDeviceDetails;
import org.buildcode.rideservice.api.model.v1_0.RideResponseModel;
import org.buildcode.rideservice.data.entity.Ride;
import org.buildcode.rideservice.data.mapper.RideMapper;
import org.buildcode.rideservice.exception.RideNotFoundException;
import org.buildcode.rideservice.repository.RideRepository;
import org.buildcode.rideservice.usecase.RideDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class RideDeviceServiceImpl implements RideDeviceService {

    @Autowired
    private RideRepository rideRepository;

    @Autowired
    private RideMapper rideMapper;

    @Override
    public RideResponseModel updateDeviceDetails(String id, RideDeviceDetails rideDeviceDetails) {
        Optional<Ride> ride = rideRepository.findById(id);

        if (ride.isEmpty()) {
            log.error("Ride not found for given id: {}", id);
            throw new RideNotFoundException("Ride couldn't found");
        }

        Ride updatedRide = ride.get();

        updatedRide.setDeviceToken(rideDeviceDetails.getDeviceToken());  // Store the device token
        rideRepository.save(updatedRide);

        return rideMapper.toRideResponseModel(updatedRide);
    }
}