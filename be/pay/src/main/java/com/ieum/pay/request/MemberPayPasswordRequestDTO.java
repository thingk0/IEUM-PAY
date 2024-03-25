package com.ieum.pay.request;

import lombok.Data;

@Data
public class MemberPayPasswordRequestDTO {
    Long memberId;
    String paymentPassword;
}
