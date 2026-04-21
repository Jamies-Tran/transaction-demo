package com.example.transactionlogdemo.application.usecase.execution.route;

import com.example.transactionlogdemo.domain.entity.route.Route;
import com.example.transactionlogdemo.domain.entity.transaction.retry.Retry;
import com.example.transactionlogdemo.domain.enums.EnumMethod;
import com.example.transactionlogdemo.domain.service.execution.route.RouteExecutionService;
import com.example.transactionlogdemo.domain.service.route.RouteService;
import com.example.transactionlogdemo.infrastructure.external.adapter.RouteExternalAdapter;
import com.example.transactionlogdemo.infrastructure.external.dto.RequestDefinition;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RouteExecutionUseCase implements RouteExecutionService {
    RouteService routeService;

    RouteExternalAdapter routeExternalAdapter;

    @Override
    public Object execute(String routeCode, RequestDefinition requestDefinition, Retry retry) {
        Route route = routeService.getByCode(routeCode)
                .orElseThrow(RuntimeException::new);
        RequestDefinition completeRequestDefinition = buildCompleteRequestDefinition(route, requestDefinition);

        return executeRequestWithRetry(completeRequestDefinition, retry);
    }

    private RequestDefinition buildCompleteRequestDefinition(Route route, RequestDefinition source) {
        EnumMethod method = getMethod(route.protocol().method());
        String uri = getUri(route);

        return source.withMethod(method).withUri(uri);
    }

    private EnumMethod getMethod(String methodCode) {
        return EnumMethod.getByCode(methodCode);
    }

    private String getUri(Route route) {
        String host = route.remote().host();
        String uri = route.path().uri();
        host = !host.endsWith("/") ? "%s/".formatted(host) : host;
        uri = !uri.startsWith("/") ? uri: uri.substring(1);
        return "%s%s".formatted(host, uri);
    }

    private Object executeRequestWithRetry(RequestDefinition def, Retry retry) {
        try {
            return routeExternalAdapter.execute(def)
                    .getBody();
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            try {
                boolean allowRetry = switch (e.getStatusCode()) {
                    case HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.BAD_GATEWAY,
                         HttpStatus.GATEWAY_TIMEOUT, HttpStatus.SERVICE_UNAVAILABLE -> true;
                    default -> false;
                };
                if (allowRetry && Objects.nonNull(retry) && retry.maxAttempts() > 0) {
                    log.info("Retry count: {}", retry.maxAttempts());
                    Thread.sleep(retry.backoff());
                    int remainRetry = retry.maxAttempts() - 1;
                    return executeRequestWithRetry(def, retry.withMaxAttempts(remainRetry));
                }
                throw new RuntimeException(e);
            } catch (InterruptedException ie) {
                throw e;
            }
        }
    }
}
