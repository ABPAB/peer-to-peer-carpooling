package org.buildcode.syncservice.usecase.impl;

import org.buildcode.syncservice.data.dto.RideUpdateKafkaPayload;
import org.buildcode.syncservice.usecase.RideUpdateService;
import org.springframework.stereotype.Service;

@Service
public class RideUpdateServiceImpl implements RideUpdateService {

    @Override
    public void update(RideUpdateKafkaPayload rideUpdateKafkaPayload) {

    }
}