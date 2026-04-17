package com.example.transactionlogdemo.application.usecase.execution.route;

import com.example.transactionlogdemo.domain.entity.context.execution.ExecutionContext;
import com.example.transactionlogdemo.domain.service.execution.route.RouteExecutionService;
import com.example.transactionlogdemo.domain.service.route.RouteService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RouteExecutionUseCase implements RouteExecutionService {
    RouteService routeService;

    @Override
    public void execute(String routeCode, ExecutionContext context) {

    }
}
