package com.ieum.common.exception.member;

import com.ieum.common.format.code.ErrorCode;
import lombok.Getter;

@Getter
public class MemberNotFoundException extends RuntimeException {

    private final ErrorCode errorCode;

    public MemberNotFoundException() {
        this.errorCode = ErrorCode.MEMBER_NOT_FOUND_ERROR;
    }
}
