package com.ieum.pay.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class FundingDonationResultResponseDTO {
    Long fundingId;
    int donationAmount;
    LocalDateTime historyDate;
}
