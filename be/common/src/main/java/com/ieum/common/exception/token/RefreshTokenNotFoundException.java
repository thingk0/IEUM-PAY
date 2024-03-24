package com.ieum.common.exception.token;

import com.ieum.common.format.code.ErrorCode;
import lombok.Getter;

@Getter
public class RefreshTokenNotFoundException extends RuntimeException {

    private final ErrorCode errorCode;

    public RefreshTokenNotFoundException() {
        this.errorCode = ErrorCode.REFRESH_TOKEN_NOT_FOUND;
    }
}
