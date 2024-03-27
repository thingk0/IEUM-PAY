package com.ieum.common.dto.feign.pay.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FundingDonationRequestDTO {
    Long memberId;
    Long fundingId;
    int donationAmount;
}
