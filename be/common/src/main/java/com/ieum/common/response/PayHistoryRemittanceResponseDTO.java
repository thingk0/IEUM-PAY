package com.ieum.common.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PayHistoryRemittanceResponseDTO {
    String name;
    int amount;
}
