package org.buildcode.synchservice.usecase.impl;

import lombok.extern.slf4j.Slf4j;
import org.buildcode.synchservice.data.entity.Ride;
import org.buildcode.synchservice.repository.RideRepository;
import org.buildcode.synchservice.usecase.RideUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RideUpdateServiceImpl implements RideUpdateService {

    @Autowired
    private RideRepository rideRepository;

    @Override
    public void update(Ride ride) {
        Ride newRide = rideRepository.save(ride);
        log.info("Created new ride! : {}", newRide);
    }
}