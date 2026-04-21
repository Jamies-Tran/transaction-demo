package com.example.transactionlogdemo.application.usecase.execution.route;

import com.example.transactionlogdemo.domain.entity.route.Route;
import com.example.transactionlogdemo.domain.enums.EnumMethod;
import com.example.transactionlogdemo.domain.service.execution.route.RouteExecutionService;
import com.example.transactionlogdemo.domain.service.route.RouteService;
import com.example.transactionlogdemo.infrastructure.external.adapter.RouteExternalAdapter;
import com.example.transactionlogdemo.infrastructure.external.dto.RequestDefinition;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RouteExecutionUseCase implements RouteExecutionService {
    RouteService routeService;

    RouteExternalAdapter routeExternalAdapter;

    @Override
    public Object execute(String routeCode, RequestDefinition requestDefinition) {
        Route route = routeService.getByCode(routeCode)
                .orElseThrow(RuntimeException::new);
        RequestDefinition completeRequestDefinition = buildCompleteRequestDefinition(route, requestDefinition);

        return routeExternalAdapter.execute(completeRequestDefinition);
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
}
