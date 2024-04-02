package com.ieum.common.format.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Topic {

    TRANSFER_RECEIVED("transfer-received"),
    FUNDING_COMPLETED("funding-completed"),
    SSE_CONNECTION_REQUEST("sse-connection-request"),
    ;

    private final String topicName;
}
