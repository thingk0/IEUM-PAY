package com.ieum.common.dto.feign.funding.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class FundingMemberDTO {
    private String nickname;
    private Long memberId;
    private Integer fundingTotalAmount;
}
