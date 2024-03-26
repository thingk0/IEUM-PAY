package com.ieum.common.dto.feign.pay.request;

import lombok.Data;


@Data
public class CardRegisterRequestDTO {
    Long memberId;
    String cardNumber;
    String cardNickname;
}

