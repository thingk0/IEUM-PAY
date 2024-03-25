package com.ieum.pay.request;

import lombok.Data;

@Data
public class PaymentChargeMoney {
    Long memberId;
    int paymentMoney;
}
