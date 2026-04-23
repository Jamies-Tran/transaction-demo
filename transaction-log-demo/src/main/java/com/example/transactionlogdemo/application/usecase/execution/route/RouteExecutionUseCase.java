package com.example.transactionlogdemo.application.usecase.execution.route;

import com.example.transactionlogdemo.domain.entity.log.TransactionLog;
import com.example.transactionlogdemo.domain.entity.route.Route;
import com.example.transactionlogdemo.domain.entity.transaction.retry.Retry;
import com.example.transactionlogdemo.domain.enums.EnumMethod;
import com.example.transactionlogdemo.domain.enums.EnumTransactionLogStatus;
import com.example.transactionlogdemo.domain.service.execution.route.RouteExecutionService;
import com.example.transactionlogdemo.domain.service.log.TransactionLogService;
import com.example.transactionlogdemo.domain.service.route.RouteService;
import com.example.transactionlogdemo.infrastructure.bootstrap.utils.jackson.ObjectMapperUtils;
import com.example.transactionlogdemo.infrastructure.external.adapter.RouteExternalAdapter;
import com.example.transactionlogdemo.infrastructure.external.dto.RequestDefinition;
import jakarta.servlet.http.HttpServletRequest;
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

    TransactionLogService transactionLogService;

    RouteExternalAdapter routeExternalAdapter;

    HttpServletRequest requestServlet;

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
            ResponseEntity<Object> response = routeExternalAdapter.execute(def);
            saveLog(def, response);
            return response.getBody();
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
                    saveLog(def, String.valueOf(e.getStatusCode().value()), remainRetry, e.getMessage());
                    return executeRequestWithRetry(def, retry.withMaxAttempts(remainRetry));
                }
                saveLog(def, String.valueOf(e.getStatusCode().value()), 0, e.getMessage());
                throw new RuntimeException(e);
            } catch (InterruptedException ie) {
                throw e;
            }
        }
    }

    private void saveLog(
            RequestDefinition request,
            ResponseEntity<Object> response
    ) {
        TransactionLog transactionLog = buildTransactionLog(request, response);
        transactionLogService.create(transactionLog);
    }

    private void saveLog(
            RequestDefinition request,
            String httpStatusCode,
            Integer retryCount,
            String errMessage
    ) {
        TransactionLog transactionLog = buildTransactionLog(request, httpStatusCode, retryCount, errMessage);
        transactionLogService.create(transactionLog);
    }

    private TransactionLog buildTransactionLog(
            RequestDefinition request,
            ResponseEntity<Object> response
    ) {
        long requestAtMillis = (long) requestServlet.getAttribute("X-Request-At");
        long responseAtMillis = System.currentTimeMillis() - requestAtMillis;
        return TransactionLog.builder()
                .transactionId(request.transactionId())
                .requestBody(ObjectMapperUtils.convertToString(response.getBody()))
                .requestParam(ObjectMapperUtils.convertToString(request.params()))
                .httpStatus(String.valueOf(response.getStatusCode().value()))
                .requestAtMillis(requestAtMillis)
                .responseAtMillis(responseAtMillis)
                .status(EnumTransactionLogStatus.SUCCESS.name())
                .build();
    }

    private TransactionLog buildTransactionLog(
            RequestDefinition request,
            String httpStatusCode,
            Integer retryCount,
            String errMessage
    ) {
        long requestAtMillis = (long) requestServlet.getAttribute("X-Request-At");
        long responseAtMillis = System.currentTimeMillis() - requestAtMillis;
        return TransactionLog.builder()
                .transactionId(request.transactionId())
                .requestParam(ObjectMapperUtils.convertToString(request.params()))
                .httpStatus(httpStatusCode)
                .requestAtMillis(requestAtMillis)
                .responseAtMillis(responseAtMillis)
                .retryCount(retryCount)
                .errMessage(errMessage)
                .status(EnumTransactionLogStatus.ERROR.name())
                .build();
    }
}
