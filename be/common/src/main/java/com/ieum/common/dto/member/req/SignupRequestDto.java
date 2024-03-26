package com.ieum.common.dto.member.req;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SignupRequestDto {

    @NotBlank(message = "휴대폰 번호는 필수입니다.")
    @Pattern(regexp = "^010-\\d{4}-\\d{4}$", message = "휴대폰 번호 형식이 잘못되었습니다.")
    private String phoneNumber;

    @NotBlank(message = "이름은 필수입니다.")
    @Pattern(regexp = "^([가-힇]){2,10}$",
             message = "이름은 한글(자음 또는 모음만 존재하는 것 제외)을 조합하여 2~10자 이내여야 합니다.")
    @Schema(description = "이름", example = "김싸피")
    private String name;

    @NotBlank(message = "닉네임은 필수입니다.")
    @Size(min = 2, max = 10, message = "닉네임은 2자 이상 10자 이하여야 합니다.")
    @Pattern(regexp = "^[가-힣a-zA-Z0-9]*$", message = "닉네임은 한글, 영문자, 숫자만 사용 가능합니다.")
    private String nickname;

    @NotBlank(message = "비밀번호는 필수입니다.")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*])(?=.*\\d).{8,20}$",
             message = "비밀번호는 영문 대소문자, 숫자, 특수문자(!, @, #, $, %, ^, &, *)를 조합하여 3~20자 이내여야 합니다.")
    @Schema(description = "비밀번호", example = "Ssafy123!@")
    private String password;

    @NotEmpty(message = "비밀번호 확인란을 반드시 입력해 주셔야 합니다.")
    @Schema(description = "비밀번호 확인", example = "Ssafy123!@")
    private String passwordConfirm;

    @NotBlank(message = "결제 비밀번호는 필수입니다.")
    @Size(min = 6, max = 6, message = "결제 비밀번호는 6자리 숫자여야 합니다.")
    @Pattern(regexp = "^\\d{4}$", message = "결제 비밀번호는 6자리 숫자여야 합니다.")
    private String paymentPassword;

}