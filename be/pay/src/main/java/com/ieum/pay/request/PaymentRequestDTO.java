package com.ieum.pay.request;

import lombok.Data;

@Data
public class PaymentRequestDTO {
    Long memberId;
    Long storeId;
    Long fundingId;
    Long cardId;
    int amount;
    int donationAmount;
    int chargeAmount;
}
