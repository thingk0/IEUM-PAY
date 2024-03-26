package com.ieum.common.exception.member;

import com.ieum.common.format.code.FailedCode;
import lombok.Getter;

@Getter
public class InvalidPhoneNumberException extends RuntimeException {

    private final FailedCode failedCode;

    public InvalidPhoneNumberException() {
        this.failedCode = FailedCode.UNVERIFIED_PHONE_NUMBER;
    }
}
