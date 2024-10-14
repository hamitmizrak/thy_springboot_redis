package com.hamitmizrak.thy_springboot_redis.exception;

// Exception Type: 201
// Exception Status:

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class _404_NotFoundException extends RuntimeException {

    // Parametreli Constructor
    public _404_NotFoundException(String message) {
        super(message);
    } //end parametreli constructor

} //end class
