package com.ieum.common.dto.member.req;

import com.ieum.common.validation.NotEqualPassword;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@NotEqualPassword
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PasswordUpdateRequestDto {

    String prevPassword;
    String newPassword;

}
