package com.hamitmizrak.thy_springboot_redis.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

// LOMBOK
@RequiredArgsConstructor

public class UniqueAddressZipCodeValidation implements ConstraintValidator<UniqueAddressZipCode, String> {

    // Posta kodu
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return false;
    }
}
