package com.hamitmizrak.thy_springboot_redis.exception;

// Exception Type: 201
// Exception Status:

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class _500_IntervalServerException extends RuntimeException {

    // Parametreli Constructor
    public _500_IntervalServerException(String message) {
        super(message);
    } //end parametreli constructor

} //end class
