package com.hamitmizrak.thy_springboot_redis.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;


@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {UniqueAddressQRCodeValidation.class})
public @interface UniqueAddressQRCode {
    String message() default "{address.qr_code.unique.validation.constraints.NotNull.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

