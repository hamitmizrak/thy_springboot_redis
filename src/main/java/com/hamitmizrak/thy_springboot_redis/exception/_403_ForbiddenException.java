package com.hamitmizrak.thy_springboot_redis.exception;

// Exception Type: 201
// Exception Status:

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class _403_ForbiddenException extends RuntimeException {

    // Parametreli Constructor
    public _403_ForbiddenException(String message) {
        super(message);
    } //end parametreli constructor

} //end class
