package com.ieum.common.dto.card;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CardResponseDto {

    private Long registeredCardId;
    private Long cardId;
    private String cardNickname;
    private String cardIssuer;
    private boolean mainCard;

}
