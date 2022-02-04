package com.test.demo.configuration;


import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import com.test.demo.model.UserProto;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {

    private String bootstrapAddress = "localhost:9092";

    public ProducerFactory<String, UserProto.User> producerFactory() {

        Map<String, Object> configProps = new HashMap<>();
        configProps.put( ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);

        configProps.put( ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put( ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, ValueSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<String, UserProto.User> kafkaTemplate() {

        return new KafkaTemplate<>(producerFactory());
    }
}