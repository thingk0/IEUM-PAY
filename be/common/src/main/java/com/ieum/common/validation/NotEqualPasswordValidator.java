package com.ieum.common.validation;

import com.ieum.common.dto.member.req.PasswordUpdateRequestDto;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NotEqualPasswordValidator implements ConstraintValidator<NotEqualPassword, PasswordUpdateRequestDto> {

    @Override
    public boolean isValid(PasswordUpdateRequestDto value, ConstraintValidatorContext context) {
        return !value.getPrevPassword().equals(value.getNewPassword());
    }
}