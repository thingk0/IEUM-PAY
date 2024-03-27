package com.ieum.funding.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FundingMemberDTO {
    private String nickname;
    private Long memberId;
    private Integer fundingTotalAmount;
}
