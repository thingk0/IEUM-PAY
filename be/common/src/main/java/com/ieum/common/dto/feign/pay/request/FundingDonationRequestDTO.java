package com.ieum.common.dto.feign.pay.request;

import lombok.Data;

@Data
public class FundingDonationRequestDTO {
    Long memberId;
    Long fundingId;
    int donationAmount;
}
