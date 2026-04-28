package com.example.transactionlogdemo.application.usecase.execution.route;

import com.example.transactionlogdemo.domain.entity.execution.result.route.RouteExecutionResult;
import com.example.transactionlogdemo.domain.entity.execution.result.workflow.WorkflowExecutionResult;
import com.example.transactionlogdemo.domain.entity.route.Route;
import com.example.transactionlogdemo.domain.entity.transaction.retry.Retry;
import com.example.transactionlogdemo.domain.enums.EnumExecutionErrorSource;
import com.example.transactionlogdemo.domain.enums.EnumMethod;
import com.example.transactionlogdemo.domain.enums.EnumTransactionResultStatus;
import com.example.transactionlogdemo.domain.service.execution.route.RouteExecutionService;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RouteExecutionUseCase implements RouteExecutionService {
    RouteService routeService;

    RouteExternalAdapter routeExternalAdapter;

    HttpServletRequest requestServlet;

    // domain chứa object và list log (fail + retry + success)
    @Override
    public RouteExecutionResult execute(String routeCode, RequestDefinition requestDefinition, Retry retry) {
        List<WorkflowExecutionResult.ExecutionResult> results = new ArrayList<>();
        Route route = routeService.getByCode(routeCode)
                .orElseThrow(RuntimeException::new);
        RequestDefinition completeRequestDefinition = buildCompleteRequestDefinition(route, requestDefinition);

        return executeRequestWithRetry(completeRequestDefinition, retry, results);
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

    private RouteExecutionResult executeRequestWithRetry(
            RequestDefinition def,
            Retry retry,
            List<WorkflowExecutionResult.ExecutionResult> results
    ) {
        try {
            ResponseEntity<Object> response = routeExternalAdapter.execute(def);
            results.add(buildTransactionExecutionResult(def, response));
            return RouteExecutionResult.builder()
                    .responseData(response.getBody())
                    .results(results)
                    .build();
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
                    results.add(buildTransactionExecutionResult(def, String.valueOf(e.getStatusCode().value()),
                            remainRetry, e.getMessage()));
                    return executeRequestWithRetry(def, retry.withMaxAttempts(remainRetry), results);
                }
                results.add(buildTransactionExecutionResult(def, String.valueOf(e.getStatusCode().value()),
                        null, e.getMessage()));
                throw new RuntimeException(e);
            } catch (InterruptedException ie) {
                throw e;
            }
        }
    }

    private WorkflowExecutionResult.ExecutionResult buildTransactionExecutionResult(
            RequestDefinition request,
            ResponseEntity<Object> response
    ) {
        long requestAtMillis = (long) requestServlet.getAttribute("X-Request-At");
        long responseAtMillis = System.currentTimeMillis() - requestAtMillis;

        return WorkflowExecutionResult.ExecutionResult.builder()
                .transactionId(request.transactionId())
                .dataResult(ObjectMapperUtils.convertToString(response.getBody()))
                .dataRequest(WorkflowExecutionResult.ExecutionResult.DataRequest.builder()
                        .params(ObjectMapperUtils.convertToString(request.params()))
                        .body(ObjectMapperUtils.convertToString(request.body()))
                        .build())
                .httpStatus(String.valueOf(response.getStatusCode().value()))
                .requestMillis(requestAtMillis)
                .responseMillis(responseAtMillis)
                .status(EnumTransactionResultStatus.SUCCESS.name())
                .build();
    }

    private WorkflowExecutionResult.ExecutionResult buildTransactionExecutionResult(
            RequestDefinition request,
            String httpStatusCode,
            Integer retryCount,
            String errMessage
    ) {
        long requestAtMillis = (long) requestServlet.getAttribute("X-Request-At");
        long responseAtMillis = System.currentTimeMillis() - requestAtMillis;
        return WorkflowExecutionResult.ExecutionResult.builder()
                .transactionId(request.transactionId())
                .dataResult(null)
                .dataRequest(WorkflowExecutionResult.ExecutionResult.DataRequest.builder()
                        .params(ObjectMapperUtils.convertToString(request.params()))
                        .body(ObjectMapperUtils.convertToString(request.body()))
                        .build())
                .httpStatus(httpStatusCode)
                .requestMillis(requestAtMillis)
                .responseMillis(responseAtMillis)
                .retryCount(retryCount)
                .errMessage(errMessage)
                .status(EnumTransactionResultStatus.ERROR.name())
                .errSourceCode(EnumExecutionErrorSource.TRANSACTION_ERR.getCode())
                .errSourceName(EnumExecutionErrorSource.TRANSACTION_ERR.getName())
                .build();
    }
}
