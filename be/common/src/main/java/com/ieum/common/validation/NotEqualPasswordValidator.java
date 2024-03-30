package com.ieum.common.validation;

import com.ieum.common.dto.member.req.PasswordUpdateRequestDto;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.StringUtils;

public class NotEqualPasswordValidator implements ConstraintValidator<NotEqualPassword, PasswordUpdateRequestDto> {

    private static final String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*])(?=.*\\d).{8,20}$";

    @Override
    public boolean isValid(PasswordUpdateRequestDto value, ConstraintValidatorContext context) {
        if (value == null || StringUtils.isBlank(value.getPrevPassword()) || StringUtils.isBlank(value.getNewPassword())) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("비밀번호를 입력해주세요.")
                   .addConstraintViolation();
            return false;
        }

        if (value.getPrevPassword().equals(value.getNewPassword())) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("새 비밀번호와 이전 비밀번호는 다르게 입력해주세요.")
                   .addConstraintViolation();
            return false;
        }

        if (!value.getNewPassword().matches(PASSWORD_PATTERN)) {
            context.disableDefaultConstraintViolation(); // 기본 메시지 비활성화
            context.buildConstraintViolationWithTemplate("비밀번호는 8~20자리, 대소문자, 숫자, 특수문자를 포함해야 합니다.")
                   .addConstraintViolation();
            return false;
        }

        return true;
    }
}