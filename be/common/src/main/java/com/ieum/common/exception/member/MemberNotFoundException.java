package com.ieum.common.exception.member;

import com.ieum.common.format.code.FailedCode;
import lombok.Getter;

@Getter
public class MemberNotFoundException extends RuntimeException {

    private final FailedCode failedCode;

    public MemberNotFoundException() {
        this.failedCode = FailedCode.MEMBER_NOT_FOUND;
    }
}
