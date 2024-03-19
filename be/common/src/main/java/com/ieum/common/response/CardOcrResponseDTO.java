package com.ieum.common.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CardOcrResponseDTO {
    String cardNumber;
    String cardCvc;
    String validThru;
}
