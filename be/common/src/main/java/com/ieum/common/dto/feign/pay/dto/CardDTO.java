package com.ieum.common.dto.feign.pay.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CardDTO {
    Long registeredCardId;
    Long cardId;
    String cardNickname;
    String cardIssuer;
    boolean mainCard;
}
