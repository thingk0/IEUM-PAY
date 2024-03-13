package com.ieum.common.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PaymentInfoResponseDTO {
    Long storeId;
    int price;
    String storeName;
    int paymoneyAmount;
    int chargeAmount;
}
