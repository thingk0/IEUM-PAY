package com.ieum.common.exception.member;

import com.ieum.common.format.code.ErrorCode;
import lombok.Getter;

@Getter
public class ExistingPhoneNumberException extends RuntimeException {

    private final ErrorCode errorCode;

    public ExistingPhoneNumberException() {
        this.errorCode = ErrorCode.PHONE_NUMBER_DUPLICATED;
    }
}
