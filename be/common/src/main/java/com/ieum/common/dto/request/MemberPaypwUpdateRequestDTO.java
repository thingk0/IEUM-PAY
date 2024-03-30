package com.ieum.common.dto.request;

import lombok.Data;

@Data
public class MemberPaypwUpdateRequestDTO {
    String authenticationKey;
    String newPaymentPassword;
}
