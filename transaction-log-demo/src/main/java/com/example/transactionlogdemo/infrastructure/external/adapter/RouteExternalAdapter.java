package com.example.transactionlogdemo.infrastructure.external.adapter;

import com.example.transactionlogdemo.domain.entity.context.execution.ExecutionContext;
import com.example.transactionlogdemo.domain.entity.route.Route;
import com.example.transactionlogdemo.domain.enums.EnumMethod;
import com.example.transactionlogdemo.infrastructure.external.dto.RequestDefinition;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriBuilder;

import java.net.URI;
import java.util.Map;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RouteExternalAdapter {
    RestTemplate restTemplate;

    public ResponseEntity<?> execute(RequestDefinition def, ExecutionContext executionContext) {

        return null;
    }

    public HttpMethod getMethod(String methodCode) {
        return EnumMethod.getByCode(methodCode)
                .getMethod();
    }
}
