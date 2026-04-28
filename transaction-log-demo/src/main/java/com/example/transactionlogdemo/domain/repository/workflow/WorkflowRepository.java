package com.example.transactionlogdemo.domain.repository.workflow;

import com.example.transactionlogdemo.domain.entity.workflow.Workflow;

import java.util.Optional;

public interface WorkflowRepository {
    Workflow upsert(Workflow workflow);

    Optional<Workflow> findByCode(String code);

    void deleteByCode(String code);
}
