package com.example.transactionlogdemo.domain.entity.workflow;

import com.example.transactionlogdemo.domain.entity.transaction.Transaction;
import lombok.With;

import java.util.List;
import java.util.Objects;

public record Workflow(
        String id,
        String name,
        List<String> transactionCodes,
        @With Boolean active
) {
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
