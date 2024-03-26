package com.ieum.common.exception.member;

import com.ieum.common.format.code.FailedCode;
import lombok.Getter;

@Getter
public class InvalidPrincipalTypeException extends RuntimeException {

    private final FailedCode failedCode;

    public InvalidPrincipalTypeException() {
        this.failedCode = FailedCode.INVALID_PRINCIPAL_TYPE;
    }
}
