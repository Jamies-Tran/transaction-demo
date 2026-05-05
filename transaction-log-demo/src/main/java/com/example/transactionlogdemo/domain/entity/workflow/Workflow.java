package com.example.transactionlogdemo.domain.entity.workflow;

import lombok.With;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public record Workflow(
        String id,
        String code,
        String name,
        Map<String, Object> dataResultSchema,
        List<WorkflowTransaction> transactions,
        @With Boolean active
) {
    public record WorkflowTransaction(
            Integer executionOrder,
            String transactionCode
    ) {}

    public Workflow {
        if (Objects.isNull(active)) {
            active = true;
        }
    }

    public Workflow activate() {
        return this.withActive(true);
    }

    public Workflow inactivate() {
        return this.withActive(false);
    }
}
