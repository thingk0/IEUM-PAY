package com.ieum.pay.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RemittanceHistoryResponseDTO {
    Long historyId;
    String name;
    int amount;
}
