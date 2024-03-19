package com.ieum.common.request;

import lombok.Data;

@Data
public class PaymentRequestDTO {
    Long storeId;
    int price;
    String storeName;
    int paymoneyAmount;
    int chargeAmount;
    String authenticationKey;
}
