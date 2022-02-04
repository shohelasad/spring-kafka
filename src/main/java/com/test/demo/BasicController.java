package com.test.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.*;

import com.test.demo.model.UserProto;

@RestController
@RequestMapping("/kafka/")
public class BasicController {

    @Autowired
    private KafkaTemplate<String, UserProto.User> kafkaTemplate;

    private static final String TOPIC = "Kafka_Example";

    @GetMapping("/publish")
    public String publish() {

        // Prepare a dummy proto message
        UserProto.User user = UserProto.User.newBuilder().setId(100).setFirstName("Radhakanta").setLastName("Ghosh").build();

        // Publish the message
        ListenableFuture<SendResult<String, UserProto.User>> future = kafkaTemplate.send(TOPIC, user);

        // Publisher listener
        future.addCallback(new ListenableFutureCallback<SendResult<String, UserProto.User>>() {

            @Override
            public void onSuccess(SendResult<String, UserProto.User> result) {
                System.out.println("Sent message=["+ result +"] with offset=[" + result.getRecordMetadata().offset() + "]");
            }
            @Override
            public void onFailure(Throwable ex) {
                System.out.println("*******************88 Unable to send message = " + ex.getMessage());
            }
        });


        return "Message published to kafka";
    }
}
