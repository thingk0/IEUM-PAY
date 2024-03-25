package com.ieum.common.exception.member;

import com.ieum.common.format.code.FailedCode;
import lombok.Getter;

@Getter
public class DuplicateNicknameChangeException extends RuntimeException {

    private final FailedCode failedCode;

    public DuplicateNicknameChangeException() {
        this.failedCode = FailedCode.DUPLICATE_NICKNAME_CHANGE;
    }
}
