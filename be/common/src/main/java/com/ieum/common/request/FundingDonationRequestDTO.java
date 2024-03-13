package com.ieum.common.request;

import lombok.Data;

@Data
public class FundingDonationRequestDTO {
    Long fundingId;
    int amount;
    String facilityName;
    int paymoneyAmount;
    int chargeAmount;
    String authenticationKey;
}
