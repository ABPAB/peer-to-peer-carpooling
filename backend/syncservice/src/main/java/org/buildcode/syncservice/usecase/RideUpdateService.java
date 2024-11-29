package org.buildcode.syncservice.usecase;

import org.buildcode.syncservice.data.dto.RideUpdateKafkaPayload;
import org.buildcode.syncservice.data.entity.Ride;

public interface RideUpdateService {

    void update(Ride ride);
}