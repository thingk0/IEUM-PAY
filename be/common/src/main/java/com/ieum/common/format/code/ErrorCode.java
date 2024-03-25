package com.ieum.common.format.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    /* 500: Global Server Error */
    GLOBAL_UNEXPECTED_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "처리 중 예기치 않은 서버 오류가 발생했습니다."),
    MEMBER_NOT_FOUND_ERROR(HttpStatus.NOT_FOUND, "존재하지 않는 회원입니다."),

    /* JWT */
    REFRESH_TOKEN_NOT_FOUND(HttpStatus.UNAUTHORIZED, "새로운 로그인이 필요합니다. 재로그인을 진행해주세요."),

    /* Member */
    PASSWORD_MISMATCH(HttpStatus.BAD_REQUEST, "비밀번호가 일치하지 않습니다."),
    PHONE_NUMBER_DUPLICATED(HttpStatus.CONFLICT, "이미 가입되어 있는 핸드폰 번호입니다."),

    /* Pay */
    // 페이머니 생성 실패
    PAYMONEY_CREATION_FAILED(HttpStatus.BAD_REQUEST, "페이머니 생성에 실패하였습니다."),

    ;

    private final HttpStatus status;
    private final String message;

}


