package com.ieum.common.dto.member.res;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProfileResponseDto {

    private Long memberId;
    private String name;
    private String nickname;
    private String gradeCode;
    private String gradeName;
    private String phoneNumber;

}
