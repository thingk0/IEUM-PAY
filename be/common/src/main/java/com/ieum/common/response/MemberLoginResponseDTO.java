package com.ieum.common.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberLoginResponseDTO {
    Long memberId;
    String name;
    String nickname;
}
