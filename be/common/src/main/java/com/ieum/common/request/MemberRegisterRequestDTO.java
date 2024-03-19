package com.ieum.common.request;

import lombok.Data;

@Data
public class MemberRegisterRequestDTO {
    String phoneNumber;
    String name;
    String nickname;
    String password;
    String paymentPassword;
}
