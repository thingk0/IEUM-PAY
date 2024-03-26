package com.ieum.common.exception.member;

import com.ieum.common.format.code.FailedCode;
import lombok.Getter;

@Getter
public class ExistingPhoneNumberException extends RuntimeException {

    private final FailedCode failedCode;

    public ExistingPhoneNumberException() {
        this.failedCode = FailedCode.PHONE_NUMBER_DUPLICATED;
    }
}
