package com.example.transactionlogdemo.domain.entity.log;

import java.time.LocalDateTime;
import java.util.List;

public record TransactionLogCriteria(
        List<LocalDateTime> timeRange
) {
}
