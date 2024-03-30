package com.ieum.pay.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PaymentHistoryResponseDTO {
    Long fundingId;
    String storeName;
    int paymentAmount;
    int donationAmount;
}
