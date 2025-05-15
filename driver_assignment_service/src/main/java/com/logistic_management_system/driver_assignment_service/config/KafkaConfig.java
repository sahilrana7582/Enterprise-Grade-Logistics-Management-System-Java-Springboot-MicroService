package com.logistic_management_system.driver_assignment_service.config;

import com.logistic_management_system.common.DriverAssignedEvent;
import com.logistic_management_system.common.ShipmentCreatedEvent;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {

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

    @Bean(name = "shipmentConsumerFactory")
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, ShipmentCreatedEvent>> userCreatedEventKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, ShipmentCreatedEvent> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory(ShipmentCreatedEvent.class, "driver_assignment_service"));
        return factory;
    }

    public <T> ProducerFactory<String, T> producerFactory(Class<T> eventType) {
        Map<String, Object> config = new HashMap<>();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        return new DefaultKafkaProducerFactory<>(config);
    }

    @Bean
    public ProducerFactory<String, DriverAssignedEvent> driverAssignedProducerFactory() {
        return producerFactory(DriverAssignedEvent.class);
    }

    @Bean
    public KafkaTemplate<String, DriverAssignedEvent> driverAssignedKafkaTemplate() {
        return new KafkaTemplate<>(driverAssignedProducerFactory());
    }


}
