package com.ieum.common.exception.member;

import com.ieum.common.format.code.FailedCode;
import lombok.Getter;

@Getter
public class PasswordMismatchException extends RuntimeException {

    private final FailedCode failedCode;

    public PasswordMismatchException() {
        this.failedCode = FailedCode.PASSWORD_MISMATCH;
    }
}
