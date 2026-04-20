package com.example.transactionlogdemo.infrastructure.external.adapter;

import com.example.transactionlogdemo.domain.entity.context.execution.ExecutionContext;
import com.example.transactionlogdemo.domain.entity.route.Route;
import com.example.transactionlogdemo.domain.entity.transaction.authentication.Authentication;
import com.example.transactionlogdemo.domain.enums.EnumMethod;
import com.example.transactionlogdemo.infrastructure.bootstrap.utils.jackson.ObjectMapperUtils;
import com.example.transactionlogdemo.infrastructure.external.dto.RequestDefinition;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Map;
import java.util.Objects;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RouteExternalAdapter {
    RestTemplate restTemplate;

    public Object execute(RequestDefinition def) {
        String url = buildUrl(def);
        HttpHeaders headers = buildHeader(def);
        Object body = buildBody(def);
        HttpEntity<Object> entity = new HttpEntity<>(body, headers);

        return restTemplate.exchange(
                url,
                def.method().getMethod(),
                entity,
                Object.class
        ).getBody();
    }

    private String buildUrl(RequestDefinition def) {
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString(def.uri());
        if (!CollectionUtils.isEmpty(def.params())) {
            for (Map.Entry<String, Object> entry : def.params().entrySet()) {
                uriComponentsBuilder.queryParam(entry.getKey(), entry.getValue());
            }
        }
        return uriComponentsBuilder.toUriString();
    }

    private HttpHeaders buildHeader(RequestDefinition def) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        if (!CollectionUtils.isEmpty(def.header())) {
            for (Map.Entry<String, String> entry : def.header().entrySet()) {
                headers.add(entry.getKey(), entry.getValue());
            }
            setAuthentication(def.authentication(), headers);
        }

        return headers;
    }

    private Object buildBody(RequestDefinition def) {
        if (Objects.isNull(def.body())) {
            return null;
        }

        return ObjectMapperUtils.convertToString(def.body());
    }

    private void setAuthentication(Authentication authentication, HttpHeaders headers) {
        switch (authentication.type()) {
            case "BEARER" -> {
                headers.add("Authorization", "Bearer %s".formatted(authentication.token()));
            }
            default -> {
                throw new RuntimeException();
            }
        }
    }
}
