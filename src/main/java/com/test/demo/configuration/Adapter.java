package com.test.demo.configuration;

import java.util.Map;

public abstract class Adapter {
    public void close() {}
    public void configure(Map<String,?> configs, boolean isKey) {}
}
