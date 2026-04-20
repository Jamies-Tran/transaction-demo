package com.example.transactionlogdemo.adapter.controller.execution;

import com.example.transactionlogdemo.adapter.api.execution.ExecutionApi;
import com.example.transactionlogdemo.application.dto.request.execution.ExecutionRequest;
import com.example.transactionlogdemo.domain.service.execution.workflow.WorkflowExecutionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ExecutionController implements ExecutionApi {
    WorkflowExecutionService workflowExecutionService;

    @Override
    public ResponseEntity<?> execute(ExecutionRequest request) {
        workflowExecutionService.execute(request.workflowId(), request.input());
        return ResponseEntity.ok().build();
    }
}
