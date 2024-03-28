package com.ieum.common.dto.feign.pay.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentHistoryPayResponseDTO {
    Long fundingId;
    String storeName;
    int paymentAmount;
    int donationAmount;
}
