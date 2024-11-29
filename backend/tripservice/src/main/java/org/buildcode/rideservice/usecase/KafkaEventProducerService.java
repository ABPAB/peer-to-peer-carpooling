package org.buildcode.rideservice.usecase;

public interface KafkaEventProducerService {
    void sendEvent(String topic, String key, String value);
    void close();
}
