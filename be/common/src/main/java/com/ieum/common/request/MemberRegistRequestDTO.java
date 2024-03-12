package com.ieum.common.request;

import lombok.Data;

@Data
public class MemberRegistRequestDTO {
    String phoneNumber;
    String name;
    String nickname;
    String password;
    String paymentPassword;
}
