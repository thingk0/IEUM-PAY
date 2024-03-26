package com.ieum.common.exception.member;

import com.ieum.common.format.code.FailedCode;
import lombok.Getter;

@Getter
public class MemberNotFoundByIdException extends RuntimeException {

    private final FailedCode failedCode;

    public MemberNotFoundByIdException() {
        this.failedCode = FailedCode.MEMBER_NOT_FOUND;
    }
}
