package com.ieum.common.dto.feign.pay.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CardOcrResponseDTO {
    String cardNumber;
    String validThru;
}
