package com.ieum.pay.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CardOcrResponseDTO {
    String cardNumber;
    String validThru;
}
