package com.ieum.common.request;

import lombok.Data;

@Data
public class MemberPaypwUpdateRequestDTO {
    String authenticationKey;
    String newPaymentPassword;
}
