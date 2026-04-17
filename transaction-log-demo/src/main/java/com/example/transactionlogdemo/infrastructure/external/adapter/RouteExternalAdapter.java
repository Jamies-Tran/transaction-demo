package com.example.transactionlogdemo.infrastructure.external.adapter;

import com.example.transactionlogdemo.domain.entity.route.Route;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RouteExternalAdapter {
    RestTemplate restTemplate;

    public ResponseEntity<?> call(Route route) {
        String host = route.remote().host();
        String uri = route.path().uri();

        return null;
    }
}
