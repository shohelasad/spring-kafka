package com.test.demo.configuration;

import com.test.demo.model.UserProto;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class ValueSerializer implements Serializer<UserProto.User> {

    @Override
    public byte[] serialize(String s, UserProto.User user) {
        return user.toByteArray();
    }

    @Override
    public void configure(Map<String, ?> map, boolean b) {

    }

    @Override
    public void close() {

    }
}
