package com.ieum.common.dto.feign.funding.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FundingDonationRequestDTO {
    private Long fundingId;
    private Long memberId;
    private Integer amount;
}
