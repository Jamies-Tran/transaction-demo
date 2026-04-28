package com.example.transactionlogdemo.domain.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum EnumExecutionErrorSource {
    TRANSACTION_ERR("TRANSACTION_ERR", "Lỗi thực thi transaction"),
    SYSTEM_ERR("SYSTEM_ERR", "Lỗi hệ thống");

    String code;
    String name;
}
