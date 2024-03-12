package com.ieum.common.request;

import lombok.Data;

@Data
public class MemberLoginRequestDTO {
    String phoneNumber;
    String password;
}
