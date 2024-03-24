package com.ieum.common.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FundingResultResponseDTO {
    String fundingTitle;
    String factilityName;
    String facilityImage;
}
