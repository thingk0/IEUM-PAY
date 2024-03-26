package com.ieum.common.dto.feign.pay.request;

import lombok.Data;

@Data
public class MemberPayPasswordRequestDTO {
    Long memberId;
    String paymentPassword;
}
