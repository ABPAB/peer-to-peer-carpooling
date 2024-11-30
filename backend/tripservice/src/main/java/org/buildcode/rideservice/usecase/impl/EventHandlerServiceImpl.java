package org.buildcode.rideservice.usecase.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.buildcode.rideservice.api.constants.KafkaConstants;
import org.buildcode.rideservice.data.dto.RideEventPayload;
import org.buildcode.rideservice.data.dto.RideNotificationPayload;
import org.buildcode.rideservice.data.entity.Ride;
import org.buildcode.rideservice.usecase.EventHandlerService;
import org.buildcode.rideservice.usecase.KafkaEventProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EventHandlerServiceImpl implements EventHandlerService {

    @Autowired
    private KafkaEventProducerService kafkaEventProducerService;

    @Autowired
    private ObjectMapper objectMapper;

    public void handleRideCreatedEvent(RideEventPayload payload, String topic) {
        try {
            String eventPayloadJson = objectMapper.writeValueAsString(payload);
            kafkaEventProducerService.sendEvent(topic, payload.getRideId(), eventPayloadJson);
            log.info("RideCreatedEvent successfully sent to Kafka for ride ID: {}", payload.getRideId());
        } catch (Exception e) {
            log.error("Error serializing RideCreatedEventPayload: {}", e.getMessage(), e);
        }
    }

    public void handleRideCreatedEvent(Ride rideDetails, String topic) {
        try {
            String eventPayloadJson = objectMapper.writeValueAsString(rideDetails);
            kafkaEventProducerService.sendEvent(topic, rideDetails.getId(), eventPayloadJson);
            log.info("RideCreatedEvent successfully sent to Kafka for ride ID: {}", rideDetails.getId());
        } catch (Exception e) {
            log.error("Error serializing RideCreatedEventPayload: {}", e.getMessage(), e);
        }
    }

    public void handleRideUpdatedEvent(RideEventPayload payload) {
        try {
            String eventPayloadJson = objectMapper.writeValueAsString(payload);
            kafkaEventProducerService.sendEvent(KafkaConstants.RIDE_UPDATED_TOPIC, payload.getRideId(), eventPayloadJson);
            log.info("RideUpdatedEvent successfully sent to Kafka.");
        } catch (Exception e) {
            log.error("Error serializing RideUpdatedEventPayload: {}", e.getMessage(), e);
        }
    }

    public void handleNotificationEvent(RideNotificationPayload payload, String topic) {
        try {
            String notificationEventPayloadJson = objectMapper.writeValueAsString(payload);
            kafkaEventProducerService.sendEvent(topic, payload.getRiderId(), notificationEventPayloadJson);
            log.info("RideNotificationEvent successfully sent to Kafka.");
        } catch (Exception e) {
            log.error("Error serializing RideNotificationEventPayload: {}", e.getMessage(), e);
        }
    }

    public void handleNotificationEvent(Ride rideDetails, String topic) {
        try {
            String notificationEventPayloadJson = objectMapper.writeValueAsString(rideDetails);
            kafkaEventProducerService.sendEvent(topic, rideDetails.getId(), notificationEventPayloadJson);
            log.info("RideNotificationEvent successfully sent to Kafka.");
        } catch (Exception e) {
            log.error("Error serializing RideNotificationEventPayload: {}", e.getMessage(), e);
        }
    }
}