package com.ieum.common.exception.member;

import com.ieum.common.format.code.FailedCode;
import lombok.Getter;

@Getter
public class MemberNotFoundByPhoneNumberException extends RuntimeException {

    private final FailedCode failedCode;

    public MemberNotFoundByPhoneNumberException() {
        this.failedCode = FailedCode.MEMBER_NOT_FOUND;
    }
}
