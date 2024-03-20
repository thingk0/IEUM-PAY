package com.ieum.common.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberResponseDTO {
    Long memberId;
    String name;
    String nickname;
    String gradeCode;
    String gradeName;
}
