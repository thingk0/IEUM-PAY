package com.ieum.common.exception.cookie;

import com.ieum.common.format.code.FailedCode;
import lombok.Getter;

@Getter
public class CookieOperationException extends RuntimeException {

    private final FailedCode failedCode;

    public CookieOperationException() {
        this.failedCode = FailedCode.COOKIE_OPERATION_FAILED;
    }
}
