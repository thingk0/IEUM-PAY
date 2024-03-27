package com.ieum.common.dto.feign.pay.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentChargeMoney {
    Long memberId;
    int paymentMoney;
}
