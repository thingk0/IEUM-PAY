package com.ieum.common.exception.pay;

import com.ieum.common.format.code.FailedCode;
import lombok.Getter;

@Getter
public class DonationHistoryNotFoundException extends RuntimeException {

    private final FailedCode failedCode;

    public DonationHistoryNotFoundException() {
        this.failedCode = FailedCode.DONATION_HISTORY_NOT_FOUND;
    }
}
