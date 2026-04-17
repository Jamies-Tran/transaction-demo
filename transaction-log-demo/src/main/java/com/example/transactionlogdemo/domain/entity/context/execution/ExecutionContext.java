package com.example.transactionlogdemo.domain.entity.context.execution;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.HashMap;
import java.util.Map;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ExecutionContext {
    @Getter
    Map<String, Object> data = new HashMap<>();

    public void put(String key, Object value) {
        data.put(key, value);
    }

    public void putAll(Map<String, Object> newData) {
        data.putAll(newData);
    }

    public Object get(String key) {
        return data.get(key);
    }
}
