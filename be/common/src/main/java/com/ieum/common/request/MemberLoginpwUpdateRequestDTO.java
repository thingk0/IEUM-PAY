package com.ieum.common.request;

import lombok.Data;

@Data
public class MemberLoginpwUpdateRequestDTO {
    String password;
    String newPassword;
}
