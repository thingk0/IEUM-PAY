package com.ieum.common.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NotEqualPasswordValidator.class)
@Documented
public @interface NotEqualPassword {

    String message() default "새로운 비밀번호와 이전 비밀번호가 동일합니다.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}