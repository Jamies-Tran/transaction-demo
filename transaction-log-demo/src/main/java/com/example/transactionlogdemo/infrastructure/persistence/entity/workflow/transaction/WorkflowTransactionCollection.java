package com.example.transactionlogdemo.infrastructure.persistence.entity.workflow.transaction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkflowTransactionCollection {
    Integer executionOrder;
    String transactionCode;
}
