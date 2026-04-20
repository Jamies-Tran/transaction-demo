package com.example.transactionlogdemo.domain.service.execution.workflow;

import java.util.Map;

public interface WorkflowExecutionService {
    void execute(String workflowId, Map<String, Object> input);
}
