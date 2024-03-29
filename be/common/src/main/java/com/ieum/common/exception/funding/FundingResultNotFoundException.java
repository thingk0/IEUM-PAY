package com.ieum.common.exception.funding;

import com.ieum.common.format.code.FailedCode;
import lombok.Getter;

@Getter
public class FundingResultNotFoundException extends RuntimeException {

    private final FailedCode failedCode;

    public FundingResultNotFoundException() {
        this.failedCode = FailedCode.FUNDING_RESULT_NOT_FOUND;
    }
}
