package com.ieum.common.dto.request;

import lombok.Data;

@Data
public class MemberLoginpwUpdateRequestDTO {
    String password;
    String newPassword;
}
