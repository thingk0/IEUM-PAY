package com.ieum.common.dto.feign.pay.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CardDTO {
    Long registeredCardId;
    Long cardId;
    String cardNickname;
    String cardIssuer;
    boolean mainCard;
}
