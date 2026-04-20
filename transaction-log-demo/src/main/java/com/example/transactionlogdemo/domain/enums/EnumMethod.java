package com.example.transactionlogdemo.domain.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpMethod;

import java.util.Objects;
import java.util.stream.Stream;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum EnumMethod {
    GET("GET", HttpMethod.GET),
    POST("POST", HttpMethod.POST),
    PUT("PUT", HttpMethod.PUT),
    PATCH("PATCH", HttpMethod.PATCH),
    DELETE("DELETE", HttpMethod.DELETE),
    OPTION("OPTION", HttpMethod.OPTIONS),
    UNDEFINED("", null);

    String code;
    HttpMethod method;

    public static EnumMethod getByCode(String code) {
        return Stream.of(values())
                .filter(m -> Objects.equals(m.getCode(), code))
                .findAny()
                .orElse(EnumMethod.UNDEFINED);
    }
}
