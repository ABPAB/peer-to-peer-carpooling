package org.buildcode.syncservice.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RideUpdateEventListener {

    @KafkaListener(topics = "ride.update", groupId = "update-group")
    public void listen(final String body) {
        log.info("Received the request to sync(update) ride data, ride: {}", body);
    }
}