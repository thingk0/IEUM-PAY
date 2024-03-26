package com.ieum.common.util;


import com.ieum.common.exception.cookie.CookieOperationException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

/**
 * 쿠키 유틸리티 클래스 쿠키 생성, 삭제, 조회 기능 제공
 */
@Slf4j
@Component
public class CookieUtil {


    /**
     * 쿠키를 생성하고 HTTP 응답에 추가합니다.
     *
     * @param key         쿠키 키
     * @param value       쿠키 값
     * @param expiredTime 쿠키 유효시간(초)
     * @param response    HTTP 응답 객체
     */
    public void addCookie(String key, String value, int expiredTime, HttpServletResponse response) {
        ResponseCookie cookie = createCookie(key, value, expiredTime);
        response.addHeader("Set-Cookie", cookie.toString());
        log.info("cookie:added, key={}, value={}, expiredTime={}", key, value, expiredTime);
    }


    /**
     * 쿠키를 삭제합니다. 삭제 과정에서 문제가 발생하면 CookieOperationException 예외를 던집니다.
     *
     * @param key      삭제할 쿠키 키
     * @param response HTTP 응답 객체
     * @throws CookieOperationException 쿠키 삭제 과정에서 문제가 발생한 경우
     */
    public void removeCookie(String key, HttpServletResponse response) {
        try {
            ResponseCookie cookie = createCookie(key, null, 0);
            response.addHeader("Set-Cookie", cookie.toString());
            log.info("cookie:removed, key={}", key);
        } catch (Exception e) {
            throw new CookieOperationException();
        }
    }


    /**
     * HTTP 요청에서 RefreshToken 쿠키 값을 가져옵니다.
     *
     * @param request HTTP 요청 객체
     * @return RefreshToken 쿠키 값, 쿠키가 없으면 null
     */
    public String getRefreshTokenValue(HttpServletRequest request) {
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if ("RefreshToken".equals(cookie.getName())) {
                    log.info("cookie:found, key=RefreshToken, value={}", cookie.getValue());
                    return cookie.getValue();
                }
            }
        }
        log.info("cookie:not_found, key=RefreshToken");
        return null;
    }


    /**
     * 쿠키 객체를 생성합니다.
     *
     * @param key         쿠키 키
     * @param value       쿠키 값
     * @param expiredTime 쿠키 유효시간(초)
     * @return 쿠키 객체
     */
    private ResponseCookie createCookie(String key, String value, int expiredTime) {
        return ResponseCookie.from(key, value)
                             .httpOnly(true)
                             .path("/")
                             .secure(true)
                             .sameSite("None")
                             .maxAge(expiredTime)
                             .build();
    }

}
