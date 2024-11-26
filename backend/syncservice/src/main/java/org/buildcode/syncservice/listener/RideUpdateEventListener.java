package org.buildcode.syncservice.listener;

import lombok.extern.slf4j.Slf4j;
import org.buildcode.syncservice.data.dto.RideUpdateKafkaPayload;
import org.buildcode.syncservice.data.mapper.RideMapper;
import org.buildcode.syncservice.usecase.RideUpdateService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RideUpdateEventListener {

    @Autowired
    private RideMapper rideMapper;

    @Autowired
    private RideUpdateService rideUpdateService;


    @KafkaListener(topics = "ride.update", groupId = "update-group")
    public void listen(final String payload) {
        log.info("Received the request to sync(update) ride data, ride: {}", payload);
        try {
            JSONObject jsonPayload = new JSONObject(payload);
            RideUpdateKafkaPayload rideUpdateKafkaPayload = rideMapper.toRideUpdateKafkaPayload(jsonPayload);
            rideUpdateService.update(rideUpdateKafkaPayload);
        } catch (JSONException jsonException) {
            log.error("Invalid message received: {}", jsonException.getMessage());
        } catch (Exception ex) {
            log.error("Some error occurred: {}", ex.getMessage());
        }
    }
}