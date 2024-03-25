package com.ieum.common.dto.member.req;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NicknameUpdateRequestDto {

    private String nickname;
}
