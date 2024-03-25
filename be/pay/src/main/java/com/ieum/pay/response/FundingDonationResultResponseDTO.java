package com.ieum.pay.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class FundingDonationResultResponseDTO {
    Long fundingId;
    int donationAmount;
}
