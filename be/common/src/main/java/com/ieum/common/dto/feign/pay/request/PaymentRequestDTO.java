package com.ieum.common.dto.feign.pay.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentRequestDTO {
    Long memberId;
    Long storeId;
    Long fundingId;
    Long cardId;
    int amount;
    int donationAmount;
    int chargeAmount;
}
