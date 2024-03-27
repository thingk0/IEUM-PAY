package com.ieum.funding.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FundingLinkRequestDTO {
    private Long fundingId;
    private Long memberId;
}
