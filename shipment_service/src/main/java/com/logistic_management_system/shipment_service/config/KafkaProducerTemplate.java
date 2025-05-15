package com.logistic_management_system.shipment_service.config;

import com.logistic_management_system.common.ShipmentCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducerTemplate {

    private final KafkaTemplate<String, ShipmentCreatedEvent> kafkaTemplate;

    private static final String TOPIC_NAME = "shipment_created_topic";  // Topic name can be externalized to properties

    public void sendShipmentCreatedEvent(ShipmentCreatedEvent event) {
        String key = event.getEventId();
        kafkaTemplate.send(TOPIC_NAME, key, event);
    }
}
