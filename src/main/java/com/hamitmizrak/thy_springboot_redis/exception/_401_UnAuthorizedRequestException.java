package com.hamitmizrak.thy_springboot_redis.exception;

// Exception Type: 201
// Exception Status:

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class _401_UnAuthorizedRequestException extends RuntimeException {

    // Parametreli Constructor
    public _401_UnAuthorizedRequestException(String message) {
        super(message);
    } //end parametreli constructor

} //end class
