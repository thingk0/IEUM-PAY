package com.ieum.pay.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public record CardRegisterRequest(String cardNumber, Long memberId, String cardNickname) {

}