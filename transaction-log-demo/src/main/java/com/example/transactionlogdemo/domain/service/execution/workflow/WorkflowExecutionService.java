package com.example.transactionlogdemo.domain.service.execution.workflow;

import com.example.transactionlogdemo.domain.entity.execution.result.workflow.WorkflowExecutionResult;

import java.util.Map;

public interface WorkflowExecutionService {
    WorkflowExecutionResult execute(String workflowCode, Map<String, Object> input);
}
