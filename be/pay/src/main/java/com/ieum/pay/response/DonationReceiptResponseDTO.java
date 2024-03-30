package com.ieum.pay.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class DonationReceiptResponseDTO {
    Long fundingId;
    LocalDateTime historyDate;
    int donationAmount;
}
