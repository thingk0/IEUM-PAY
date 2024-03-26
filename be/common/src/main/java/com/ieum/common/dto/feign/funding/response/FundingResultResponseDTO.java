package com.ieum.common.dto.feign.funding.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class FundingResultResponseDTO {
    private String fundingTitle;
    private String facilityName;
    private String facilityImage;
}
