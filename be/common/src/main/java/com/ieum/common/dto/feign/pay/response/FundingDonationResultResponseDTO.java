package com.ieum.common.dto.feign.pay.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class FundingDonationResultResponseDTO {
    Long fundingId;
    int donationAmount;
}
