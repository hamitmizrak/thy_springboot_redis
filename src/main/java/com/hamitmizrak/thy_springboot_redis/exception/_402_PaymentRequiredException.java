package com.hamitmizrak.thy_springboot_redis.exception;

// Exception Type: 201
// Exception Status:

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.PAYMENT_REQUIRED)
public class _402_PaymentRequiredException extends RuntimeException {

    // Parametreli Constructor
    public _402_PaymentRequiredException(String message) {
        super(message);
    } //end parametreli constructor

} //end class
