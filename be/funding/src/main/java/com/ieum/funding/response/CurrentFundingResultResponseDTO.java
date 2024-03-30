package com.ieum.funding.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CurrentFundingResultResponseDTO {
    private Long fundingId;
    private String facilityName;
    private String facilityImage;
    private Integer fundingTotalAmount;
    private Long fundingCount;
}
