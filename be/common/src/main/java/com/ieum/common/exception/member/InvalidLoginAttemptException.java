package com.ieum.common.exception.member;

import com.ieum.common.format.code.FailedCode;
import lombok.Getter;

@Getter
public class InvalidLoginAttemptException extends RuntimeException {

    private final FailedCode failedCode;

    public InvalidLoginAttemptException() {
        this.failedCode = FailedCode.LOGIN_FAILED;
    }
}
