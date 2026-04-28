package com.example.transactionlogdemo.adapter.controller.execution;

import com.example.transactionlogdemo.adapter.api.execution.ExecutionApi;
import com.example.transactionlogdemo.application.dto.request.execution.ExecutionRequest;
import com.example.transactionlogdemo.application.dto.response.execution.result.workflow.WorkflowExecutionResultResponse;
import com.example.transactionlogdemo.application.mapper.response.execution.result.workflow.WorkflowExecutionResultResponseMapper;
import com.example.transactionlogdemo.domain.entity.execution.result.workflow.WorkflowExecutionResult;
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

    WorkflowExecutionResultResponseMapper responseMapper;

    @Override
    public ResponseEntity<WorkflowExecutionResultResponse> execute(ExecutionRequest request) {
        WorkflowExecutionResult result = workflowExecutionService.execute(request.workflowCode(), request.input());
        return ResponseEntity.ok(responseMapper.toDto(result));
    }
}
