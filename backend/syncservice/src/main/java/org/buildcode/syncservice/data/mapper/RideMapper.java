package org.buildcode.syncservice.data.mapper;

import org.buildcode.syncservice.data.dto.RideUpdateKafkaPayload;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class RideMapper {

    public RideUpdateKafkaPayload toRideUpdateKafkaPayload(JSONObject jsonPayload) {
        return null;
    }
}