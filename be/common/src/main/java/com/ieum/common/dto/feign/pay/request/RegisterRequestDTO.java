package com.ieum.common.dto.feign.pay.request;

import lombok.Data;

@Data
public class RegisterRequestDTO {
    Long memberId;
    String paymentPassword;
}
