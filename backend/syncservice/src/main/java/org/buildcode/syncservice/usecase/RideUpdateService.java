package org.buildcode.syncservice.usecase;

import org.buildcode.syncservice.data.dto.RideUpdateKafkaPayload;

public interface RideUpdateService {

    void update(RideUpdateKafkaPayload rideUpdateKafkaPayload);
}