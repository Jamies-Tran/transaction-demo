package com.example.transactionlogdemo.infrastructure.persistence.entity.workflow;

import com.example.transactionlogdemo.infrastructure.persistence.entity.workflow.transaction.WorkflowTransactionCollection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("workflows")
public class WorkflowCollection {
    String id;
    String name;
    String code;
    List<WorkflowTransactionCollection> transactions;
    Boolean active;
}
