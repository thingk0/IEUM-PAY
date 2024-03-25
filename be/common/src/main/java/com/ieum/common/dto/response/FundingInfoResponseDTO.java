package com.ieum.common.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FundingInfoResponseDTO {
    Long fundingId;
    int amount;
    String facilityName;
    int paymoneyAmount;
    int chargeAmount;
}
