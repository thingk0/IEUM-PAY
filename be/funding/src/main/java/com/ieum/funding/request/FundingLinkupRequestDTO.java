package com.ieum.funding.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FundingLinkupRequestDTO {
    private Long fundingId;
    private Long memberId;
    private String nickname;
}
