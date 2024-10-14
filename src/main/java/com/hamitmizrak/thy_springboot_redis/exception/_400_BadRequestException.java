package com.hamitmizrak.thy_springboot_redis.exception;

// Exception Type: 201
// Exception Status:

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class _400_BadRequestException extends RuntimeException {

    // Parametreli Constructor
    public _400_BadRequestException(String message) {
        super(message);
    } //end parametreli constructor

} //end class
