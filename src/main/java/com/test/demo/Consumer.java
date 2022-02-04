package com.test.demo;

import com.test.demo.model.UserProto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(Consumer.class);

    @KafkaListener(topics = "Kafka_Example", groupId = "hello")
    public void consume(UserProto.User payload) {

        LOGGER.warn("Consumed event : {} ", payload);
    }
}
