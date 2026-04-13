package com.example.transactionlogdemo.domain.service.workflow;

import com.example.transactionlogdemo.domain.entity.workflow.Workflow;

import java.util.Optional;

public interface WorkflowService {
    Workflow create(Workflow workflow);

    Optional<Workflow> getById(String workflowId);

    Workflow inactive(String workflowId);

    Workflow active(String workflowId);

    void remove(String workflowId);
}
