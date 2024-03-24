package com.ieum.common.dto.token;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TokenInfo {

    private Long memberId;
    private String phoneNumber;
    private String accessToken;
    private String refreshToken;

}
