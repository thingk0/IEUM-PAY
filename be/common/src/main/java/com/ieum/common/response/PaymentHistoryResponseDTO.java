package com.ieum.common.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PaymentHistoryResponseDTO {
    String storeName;
    int paymentAmount;
    int donationAmount;
    String facilityName;
}
