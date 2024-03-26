package com.ieum.common.exception.feign;

import com.ieum.common.format.code.FailedCode;
import lombok.Getter;

@Getter
public class PaymentServiceUnavailableException extends RuntimeException {

    private final FailedCode failedCode;

    public PaymentServiceUnavailableException() {
        this.failedCode = FailedCode.RAISED_UNEXPECTED_ERROR;
    }
}
