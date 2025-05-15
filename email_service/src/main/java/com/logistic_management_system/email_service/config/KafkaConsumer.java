package com.logistic_management_system.email_service.config;


import com.logistic_management_system.common.DriverAssignedEvent;
import com.logistic_management_system.common.ShipmentCreatedEvent;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumer {

    public <T> ConsumerFactory<String, T> consumerFactory(Class<T> eventType, String groupId) {
        Map<String, Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        config.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);

        JsonDeserializer<T> deserializer = new JsonDeserializer<>(eventType);
        deserializer.addTrustedPackages("*");

        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(), deserializer);
    }

    @Bean(name = "emailConsumerFactory")
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, ShipmentCreatedEvent>> userCreatedEventKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, ShipmentCreatedEvent> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory(ShipmentCreatedEvent.class, "email-service"));
        return factory;
    }

    @Bean(name = "driverAssignmentConsumerFactory")
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, DriverAssignedEvent>> driverAssignmentEventKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, DriverAssignedEvent> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory(DriverAssignedEvent.class, "email-service"));
        return factory;
    }


}
