package com.ieum.pay.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PaymentHistoryResponseDTO {
    String storeName;
    int paymentAmount;
    int donationAmount;
}
