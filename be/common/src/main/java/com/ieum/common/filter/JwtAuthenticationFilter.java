package com.ieum.common.filter;

import com.ieum.common.format.response.FilterResponse;
import com.ieum.common.jwt.TokenProvider;
import com.ieum.common.service.TokenService;
import com.ieum.common.util.CookieUtil;
import com.ieum.common.util.FilterUtil;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final TokenProvider tokenProvider;
    private final TokenService tokenService;
    private final CookieUtil cookieUtil;
    private final FilterUtil filterUtil;
    private final FilterResponse filterResponse;

    private AntPathMatcher pathMatcher;
    private Map<String, String> filterPaths;

    @PostConstruct
    public void init() {
        this.filterPaths = filterUtil.getPaths();
        this.pathMatcher = new AntPathMatcher();
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        log.info("URI: {} {}", request.getMethod(), request.getRequestURI());

        /* 토큰 필터 패스: 정의된 URL 패턴에 맞는 경우 토큰 검사를 건너뜁니다. */
        if (shouldFilter(request.getRequestURI())) {
            filterChain.doFilter(request, response);
            return;
        }

        String accessToken = tokenService.extractAccessToken(request);
        String refreshToken = cookieUtil.getRefreshTokenValue(request);

        boolean accessTokenValid = (accessToken != null) && tokenProvider.validateToken(accessToken);
        boolean refreshTokenValid = (refreshToken != null) && tokenProvider.validateToken(refreshToken);
        Long memberIdFromAccessToken = accessToken != null ? tokenProvider.extractMemberIdFromToken(accessToken) : null;

        if (!accessTokenValid && !refreshTokenValid) {
            // Case 1: << 액세스 토큰 >> 과 << 리프레시 토큰 >> 모두 만료된 경우
            log.warn("access_token_valid={}, refresh_token_valid={}, action={}, status={}",
                     false, false, "DENY_ACCESS", "BOTH_TOKENS_INVALID");
            filterResponse.sendLoginRequiredResponse(response);
        } else if (!accessTokenValid) {
            // Case 2: << 액세스 토큰 >> 은 만료됐지만 << 리프레시 토큰 >> 은 유효한 경우
            log.info("access_token_valid={}, refresh_token_valid={}, action={}, memberId={}, status={}",
                     false, true, "ISSUE_NEW_TOKEN", memberIdFromAccessToken, "ACCESS_TOKEN_EXPIRED");
            filterResponse.sendTokenReissueResponse(response);
        } else {
            // Case 3: << 액세스 토큰 >> 은 유효하지만 << 리프레시 토큰 >> 이 만료된 경우
            // Case 4: << 액세스 토큰 >> 과 << 리프레시 토큰 >> 모두 유효한 경우
            String action = refreshTokenValid ? "CONTINUE_WITH_REQUEST" : "CONTINUE_WITH_EXPIRED_REFRESH";
            String status = refreshTokenValid ? "BOTH_TOKENS_VALID" : "REFRESH_TOKEN_EXPIRED";
            log.info("access_token_valid={}, refresh_token_valid={}, action={}, memberId={}, status={}",
                     true, refreshTokenValid, action, memberIdFromAccessToken, status);

            setAuthentication(memberIdFromAccessToken, request);
            filterChain.doFilter(request, response);
        }
    }

    private void setAuthentication(Long memberId, HttpServletRequest request) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
            memberId,
            "",
            Collections.singleton(new SimpleGrantedAuthority("USER"))
        );
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private boolean shouldFilter(String path) {
        return filterPaths.values()
                          .stream()
                          .anyMatch(pattern -> pathMatcher.match(pattern, path));
    }

}
