package com.ieum.common.exception.token;

import com.ieum.common.format.code.FailedCode;
import lombok.Getter;

@Getter
public class RefreshTokenNotFoundException extends RuntimeException {

    private final FailedCode failedCode;

    public RefreshTokenNotFoundException() {
        this.failedCode = FailedCode.REFRESH_TOKEN_NOT_FOUND;
    }
}
