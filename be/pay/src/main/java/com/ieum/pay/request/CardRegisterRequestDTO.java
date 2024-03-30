package com.ieum.pay.request;

import lombok.Data;


@Data
public class CardRegisterRequestDTO {
    Long memberId;
    String cardNumber;
    String cardNickname;
}

