package com.ieum.common.format.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ResponseCode {

    /* 회원 */
    MEMBER_SIGNUP(HttpStatus.CREATED, "회원가입 성공"),

    /* 결제 */
    PAYMENT(HttpStatus.CREATED, "결제 시도 성공"),
    PAYMENT_NEED_RECHARGE_FAILED(HttpStatus.BAD_REQUEST, "잔액 부족으로 결제 실패"),
    PAYMENT_EXCEED_LIMIT(HttpStatus.BAD_REQUEST, "카드 한도 초과로 결제 실패"),

    /* 카드 */
    CARD_EXPIRED(HttpStatus.BAD_REQUEST, "카드 유효기간 만료로 결제 실패"),
    CARD_LOST(HttpStatus.BAD_REQUEST, "분실된 카드로 결제 실패"),


    /* 은행 */
    BANK_MAINTENANCE(HttpStatus.SERVICE_UNAVAILABLE, "은행 점검으로 인한 결제 실패"),

    ;

    private final HttpStatus status;
    private final String message;

}
