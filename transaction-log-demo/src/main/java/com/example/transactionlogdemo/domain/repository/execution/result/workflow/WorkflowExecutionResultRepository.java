package com.example.transactionlogdemo.domain.repository.execution.result.workflow;

import com.example.transactionlogdemo.domain.entity.execution.result.workflow.WorkflowExecutionResult;

import java.util.Optional;

public interface WorkflowExecutionResultRepository {
    WorkflowExecutionResult save(WorkflowExecutionResult workflowExecutionResult);

    Optional<WorkflowExecutionResult> findByCode(String code);
}
