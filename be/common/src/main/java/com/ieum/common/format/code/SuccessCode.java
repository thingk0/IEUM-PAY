package com.ieum.common.format.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum SuccessCode {

    /* 공통 */
    SUCCESS(HttpStatus.OK, "작업이 성공적으로 완료되었습니다."),
    MAIN_PAGE_FETCH_SUCCESSFUL(HttpStatus.OK, "메인 페이지 조회가 성공적으로 완료되었습니다."),

    /* 회원 */
    MEMBER_SIGNUP_SUCCESSFUL(HttpStatus.CREATED, "회원가입이 성공적으로 완료되었습니다."),
    MEMBER_PROFILE_FETCHED(HttpStatus.OK, "회원 프로필 조회가 성공적으로 완료되었습니다."),
    MEMBER_WITHDRAWAL_SUCCESSFUL(HttpStatus.OK, "회원 탈퇴가 성공적으로 완료되었습니다."),
    MEMBER_EXISTS_BY_PHONE_NUMBER(HttpStatus.OK, "전화번호로 회원 존재 여부 확인이 성공적으로 완료되었습니다."),
    MEMBER_LOGOUT_SUCCESSFUL(HttpStatus.OK, "로그아웃이 성공적으로 완료되었습니다."),
    MEMBER_LOGIN_SUCCESSFUL(HttpStatus.OK, "로그인이 성공적으로 완료되었습니다."),
    NICKNAME_CHANGE_SUCCESSFUL(HttpStatus.OK, "닉네임 변경이 성공적으로 완료되었습니다."),
    PASSWORD_CHANGE_SUCCESSFUL(HttpStatus.OK, "비밀번호 변경이 성공적으로 완료되었습니다."),
    RECIPIENT_FETCH_SUCCESSFUL(HttpStatus.OK, "전화번호로 회원 조회가 성공적으로 완료되었습니다."),

    /* 결제 */
    PAYMENT_INITIATED(HttpStatus.CREATED, "결제가 성공적으로 시작되었습니다."),

    ;

    private final HttpStatus status;
    private final String message;

}

