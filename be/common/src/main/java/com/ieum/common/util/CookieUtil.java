package com.ieum.common.util;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CookieUtil {

    public void addCookie(String key, String value, int expiredTime, HttpServletResponse response) {
        response.addHeader("Set-Cookie", createCookie(key, value, expiredTime).toString());
    }

    public void removeCookie(String key, HttpServletResponse response) {
        response.addHeader("Set-Cookie", createCookie(key, null, 0).toString());
    }

    public String getRefreshTokenValue(HttpServletRequest request) {
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if ("RefreshToken".equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

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

