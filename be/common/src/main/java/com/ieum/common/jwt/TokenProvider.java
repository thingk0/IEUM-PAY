package com.ieum.common.jwt;

import com.ieum.common.domain.Members;
import com.ieum.common.dto.token.TokenInfo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TokenProvider {

    private final String SECRET_KEY;
    private Key key;

    @Getter
    private final int ACCESS_TOKEN_TIME;

    @Getter
    private final int REFRESH_TOKEN_TIME;

    public TokenProvider(@Value("${jwt.access-time}") final int ACCESS_TOKEN_TIME,
                         @Value("${jwt.refresh-time}") final int REFRESH_TOKEN_TIME,
                         @Value("${jwt.secret-key}") final String SECRET_KEY) {
        this.ACCESS_TOKEN_TIME = ACCESS_TOKEN_TIME;
        this.REFRESH_TOKEN_TIME = REFRESH_TOKEN_TIME;
        this.SECRET_KEY = SECRET_KEY;
        this.key = Keys.hmacShaKeyFor(Base64.getDecoder().decode(this.SECRET_KEY));
    }

    public TokenInfo generateTokenInfo(Members members) {
        return TokenInfo.builder()
                        .memberId(members.getId())
                        .phoneNumber(members.getPhoneNumber())
                        .accessToken(createAccessToken(members))
                        .refreshToken(createRefreshToken())
                        .build();
    }

    public String createAccessToken(Members members) {
        return Jwts.builder()
                   .setSubject(String.valueOf(members.getId()))
                   .claim("phone_number", members.getPhoneNumber())
                   .claim("grade_code", members.getGradeCode().getCode())
                   .claim("grade_name", members.getGradeCode().getName())
                   .claim("name", members.getName())
                   .claim("nickname", members.getNickname())
                   .setExpiration(new Date(
                       System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(ACCESS_TOKEN_TIME)))
                   .signWith(key)
                   .compact();
    }

    private String createRefreshToken() {
        return Jwts.builder()
                   .setExpiration(new Date(
                       System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(REFRESH_TOKEN_TIME)))
                   .signWith(key)
                   .compact();
    }

    private Claims parseClaims(String token) {
        try {
            return Jwts.parserBuilder()
                       .setSigningKey(key)
                       .build()
                       .parseClaimsJws(token)
                       .getBody();
        } catch (ExpiredJwtException e) {
            log.info("만료된 토큰, 사용자 연락처: {}", e.getClaims().getSubject());
            return e.getClaims();
        } catch (JwtException e) {
            log.error("토큰 파싱 실패: {}", e.getMessage());
            throw new JwtException("토큰 파싱 실패: {}", e);
        }
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            log.info("만료된 JWT 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            log.info("지원되지 않는 JWT 토큰입니다.");
        } catch (MalformedJwtException e) {
            log.info("잘못된 형식의 JWT 토큰입니다.");
        } catch (SignatureException e) {
            log.info("잘못된 JWT 서명입니다.");
        } catch (IllegalArgumentException e) {
            log.info("JWT 토큰이 잘못되었습니다.");
        } catch (JwtException e) {
            log.info("JWT 토큰 처리 중 알 수 없는 예외가 발생했습니다.");
        }
        return false;
    }

    public Long extractMemberIdFromToken(String token) {
        return Long.parseLong(parseClaims(token).getSubject());
    }

}
