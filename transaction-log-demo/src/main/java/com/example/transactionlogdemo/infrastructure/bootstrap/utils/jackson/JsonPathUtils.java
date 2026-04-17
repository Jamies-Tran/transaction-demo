package com.example.transactionlogdemo.infrastructure.bootstrap.utils.jackson;

import com.jayway.jsonpath.JsonPath;

public class JsonPathUtils {
    public static Object read(Object source, String path) {
        return JsonPath.read(source, path);
    }
}
