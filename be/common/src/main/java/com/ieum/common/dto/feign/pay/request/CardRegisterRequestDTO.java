package com.ieum.common.dto.feign.pay.request;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class CardRegisterRequestDTO {
    Long memberId;
    String cardNumber;
    String cardNickname;
}

