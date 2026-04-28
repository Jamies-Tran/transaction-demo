package com.example.transactionlogdemo.adapter.api.execution;

import com.example.transactionlogdemo.application.dto.request.execution.ExecutionRequest;
import com.example.transactionlogdemo.application.dto.response.execution.result.workflow.WorkflowExecutionResultResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/demo/execution")
public interface ExecutionApi {
    @PostMapping
    ResponseEntity<WorkflowExecutionResultResponse> execute(@RequestBody ExecutionRequest request);
}
