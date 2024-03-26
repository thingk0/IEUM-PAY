package com.ieum.common.dto.feign.funding.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class FundingInfoResponseDTO {
    private Long fundingId;
    private Integer amount;
    private String facilityName;
}
