package com.ieum.common.dto.feign.funding.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FundingMemberDTO {
    private String nickname;
    private String gradeCode;
    private Long memberId;
    private Integer fundingTotalAmount;
}
