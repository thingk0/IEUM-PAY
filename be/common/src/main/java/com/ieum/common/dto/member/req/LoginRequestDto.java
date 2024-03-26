package com.ieum.common.dto.member.req;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginRequestDto {

    @NotBlank(message = "휴대폰 번호는 필수입니다.")
    @Pattern(regexp = "^010-\\d{4}-\\d{4}$", message = "휴대폰 번호 형식이 잘못되었습니다.")
    private String phoneNumber;

    @NotBlank(message = "비밀번호는 필수입니다.")
    private String password;

}
