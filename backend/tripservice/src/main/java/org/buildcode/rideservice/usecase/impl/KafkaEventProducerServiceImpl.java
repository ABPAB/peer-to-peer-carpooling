package org.buildcode.rideservice.usecase.impl;


import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.buildcode.rideservice.api.constants.ApiConstants;
import org.buildcode.rideservice.usecase.KafkaEventProducerService;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class KafkaEventProducerServiceImpl implements KafkaEventProducerService {

    private final KafkaProducer<String, String> producer;

    public KafkaEventProducerServiceImpl() {
        // Set Kafka producer properties
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, ApiConstants.KAFKA_BASE_URL);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        // Create the Kafka producer instance
        this.producer = new KafkaProducer<>(properties);
    }

    @Override
    public void sendEvent(String topic, String key, String value) {
        ProducerRecord<String, String> record = new ProducerRecord<>(topic, key, value);
        producer.send(record, (metadata, exception) -> {
            if (exception != null) {
                System.err.println("Error sending message: " + exception.getMessage());
            } else {
                System.out.println("Event sent to Kafka topic " + metadata.topic() + " with offset " + metadata.offset());
            }
        });
    }

    @Override
    public void close() {
        // Close the producer when done
        producer.close();
    }
}
