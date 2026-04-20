package com.example.transactionlogdemo.infrastructure.bootstrap.utils.jackson;

import com.jayway.jsonpath.JsonPath;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
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

    public static Map<String, String> read(Class<?> clazz) {
        Map<String, String> result = new LinkedHashMap<>();
        traverse("", clazz, result);
        return result;
    }

    private static void traverse(String prefix, Class<?> source, Map<String, String> result) {
        for (Field f : source.getDeclaredFields()) {
            String fieldName = f.getName();
            String path = StringUtils.hasText(prefix)
                    ? "%s.%s".formatted(prefix, fieldName)
                    : fieldName;
            if (isPrimitive(f.getType())) {
                result.put(fieldName, "$.%s".formatted(path));
            } else {
                traverse(path, f.getType(), result);
            }
        }
    }

    private static Boolean isPrimitive(Class<?> type) {
        return type.isPrimitive()
                || Objects.equals(type, String.class)
                || Objects.equals(type, Boolean.class)
                || Number.class.isAssignableFrom(type);
    }
}
