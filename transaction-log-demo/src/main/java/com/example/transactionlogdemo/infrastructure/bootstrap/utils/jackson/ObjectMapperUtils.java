package com.example.transactionlogdemo.infrastructure.bootstrap.utils.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ObjectMapperUtils {
    private static ObjectMapper objectMapper;

    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        ObjectMapperUtils.objectMapper = objectMapper;
    }

    public static String convertToString(Map<String, Object> map) {
        try {
            return objectMapper.writeValueAsString(map);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static Map<String, String> buildMapping(Map<String, Object> input) {
        Map<String, String> result = new HashMap<>();
        traverse("", input, result);

        return result;
    }

    @SuppressWarnings("unchecked")
    private static void traverse(String path, Map<String, Object> current, Map<String, String> result) {
        for (Map.Entry<String, Object> c : current.entrySet()) {
            String key = c.getKey();
            Object value = c.getValue();
            String currentPath = StringUtils.hasText(path) ? ".%s".formatted(c.getKey()) : "?.%s".formatted(c.getKey());
            if (value instanceof Map<?, ?>) {
                traverse(currentPath, (Map<String, Object>) value, result);
            }

            result.put(key, currentPath);
        }
    }
}
