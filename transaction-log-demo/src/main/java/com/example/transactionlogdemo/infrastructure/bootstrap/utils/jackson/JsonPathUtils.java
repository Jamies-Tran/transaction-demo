package com.example.transactionlogdemo.infrastructure.bootstrap.utils.jackson;

import com.jayway.jsonpath.JsonPath;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class JsonPathUtils {
    public static Object read(Object source, String path) {
        return JsonPath.read(source, path);
    }

    public static String read(String source, String path) {
        return JsonPath.read(source, path);
    }

    public static Object mapJson(Object mapping, Object source) {
        if (Objects.isNull(mapping) || Objects.isNull(source)) {
            return Map.of();
        }
        if (mapping instanceof Map) {
            Map<String, Object> result = new HashMap<>();
            Map<String, Object> map = (Map<String, Object>) mapping;
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                result.put(entry.getKey(), mapJson(entry.getValue(), source));
            }
            return result;
        }

        if (mapping instanceof String path) {
            if (path.startsWith("$.")) {
                try {
                    return JsonPath.read(source, path);
                } catch (Exception e) {
                    throw new RuntimeException();
                }
            }
        }

        return null;
    }
}
