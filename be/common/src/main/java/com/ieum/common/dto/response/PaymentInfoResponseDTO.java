package com.ieum.common.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PaymentInfoResponseDTO {
    Long storeId;
    int price;
    String cardNickname;
    String storeName;
    String facilityName;
    int paymoneyAmount;
    int chargeAmount;
    int donationMoney;
}
