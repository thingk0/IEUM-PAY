package com.ieum.common.exception.token;

import com.ieum.common.format.code.FailedCode;
import lombok.Getter;

@Getter
public class TokenOperationException extends RuntimeException {

    private final FailedCode failedCode;

    public TokenOperationException() {
        this.failedCode = FailedCode.TOKEN_OPERATION_FAILED;
    }
}
