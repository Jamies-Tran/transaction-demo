package com.example.transactionlogdemo.domain.entity.workflow;

import com.example.transactionlogdemo.domain.entity.transaction.Transaction;
import lombok.With;

import java.util.List;
import java.util.Objects;

public record Workflow(
        String id,
        String code,
        String name,
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
