package com.ieum.pay.request;

import lombok.Data;

@Data
public class FundingDonationRequestDTO {
    Long memberId;
    Long fundingId;
    int donationAmount;
    Long cardId;
}
