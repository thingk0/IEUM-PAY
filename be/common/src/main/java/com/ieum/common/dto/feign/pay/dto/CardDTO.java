package com.ieum.common.dto.feign.pay.dto;

import lombok.*;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CardDTO {
    Long registeredCardId;
    Long cardId;
    String cardNickname;
    String cardIssuer;
    @Setter
    boolean mainCard;
}
