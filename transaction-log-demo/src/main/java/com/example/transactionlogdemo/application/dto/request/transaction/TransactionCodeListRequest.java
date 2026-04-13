package com.example.transactionlogdemo.application.dto.request.transaction;

import java.util.List;

public record TransactionCodeListRequest(
        List<String> transactionCodes
) {
}
