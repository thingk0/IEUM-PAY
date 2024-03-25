package com.ieum.common.exception.member;

import com.ieum.common.format.code.ErrorCode;
import lombok.Getter;

@Getter
public class PasswordMismatchException extends RuntimeException {

    private final ErrorCode errorCode;

    public PasswordMismatchException() {
        this.errorCode = ErrorCode.PASSWORD_MISMATCH;
    }
}
