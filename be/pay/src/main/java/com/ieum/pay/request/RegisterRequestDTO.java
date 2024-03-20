package com.ieum.pay.request;

import lombok.Data;

@Data
public class RegisterRequestDTO {
    Long memberId;
    String paymentPassword;
}
