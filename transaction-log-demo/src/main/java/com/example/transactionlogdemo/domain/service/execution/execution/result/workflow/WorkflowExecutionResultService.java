package com.example.transactionlogdemo.domain.service.execution.execution.result.workflow;

import com.example.transactionlogdemo.domain.entity.execution.result.workflow.WorkflowExecutionResult;

import java.util.List;
import java.util.Optional;

public interface WorkflowExecutionResultService {
    WorkflowExecutionResult create(WorkflowExecutionResult workflowExecutionResult);

    Optional<WorkflowExecutionResult> getByCode(String code);
}
