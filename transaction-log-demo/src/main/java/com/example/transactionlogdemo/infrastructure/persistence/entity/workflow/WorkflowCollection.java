package com.example.transactionlogdemo.infrastructure.persistence.entity.workflow;

import com.example.transactionlogdemo.infrastructure.persistence.entity.workflow.transaction.WorkflowTransactionCollection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("workflows")
public class WorkflowCollection {
    String id;
    String name;
    String code;
    Map<String, Object> dataResultSchema;
    List<WorkflowTransactionCollection> transactions;
    Boolean active;
}
