package com.ieum.common.dto.request;

import lombok.Data;

@Data
public class PaymentRequestDTO {
    Long storeId;
    int price;
    String storeName;
    int paymoneyAmount;
    int chargeAmount;
    int donationMoney;
    String authenticationKey;
}
