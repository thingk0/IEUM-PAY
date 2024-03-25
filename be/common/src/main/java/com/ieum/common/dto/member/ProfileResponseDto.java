package com.ieum.common.dto.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ProfileResponseDto {

    private Long memberId;
    private String name;
    private String nickname;
    private String gradeCode;
    private String gradeName;
    private String phoneNumber;

}
