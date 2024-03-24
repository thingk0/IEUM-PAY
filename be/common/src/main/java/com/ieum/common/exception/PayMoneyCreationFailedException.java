package com.ieum.common.exception;

import com.ieum.common.format.code.ErrorCode;
import lombok.Getter;

@Getter
public class PayMoneyCreationFailedException extends RuntimeException {

    private final ErrorCode errorCode;

    public PayMoneyCreationFailedException() {
        this.errorCode = ErrorCode.PAYMONEY_CREATION_FAILED;
    }
}
