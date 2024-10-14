package com.hamitmizrak.thy_springboot_redis.error;

import java.util.Date;
import java.util.Map;

public class ApiResult {

    // sem pvc
    // Field
    private Integer status;
    private String error;
    private String message;
    private String path;
    private Map<String,String> validationErrors;
    private Date createdDate;

    // Parametresiz Constructor
    public ApiResult() {
    }

    // Parametreli Constructor (pms)
    public ApiResult(String path, String message, Integer status) {
        this.path = path;
        this.message = message;
        this.status = status;
    }

    // Parametreli Constructor (pmes)
    public ApiResult(String path, String message,String error, Integer status) {
        this.path = path;
        this.message = message;
        this.error = error;
        this.status = status;
    } //end constructor

} //end ApiResult
