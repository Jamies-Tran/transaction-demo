package com.example.transactionlogdemo.infrastructure.persistence.entity.execution.result.transaction;

import com.example.transactionlogdemo.infrastructure.persistence.entity.execution.result.transaction.data.DataRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExecutionResultCollection {
    String transactionId;
    DataRequest dataRequest;
    String dataResult;
    Integer retryCount;
    Long requestMillis;
    Long responseMillis;
    String errMessage;
    String httpStatus;
    String status;
    String errSourceCode;
    String errSourceName;
}
