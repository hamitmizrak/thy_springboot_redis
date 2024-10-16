package com.hamitmizrak.thy_springboot_redis.error;

import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.Date;
import java.util.Map;

// LOMBOK
@AllArgsConstructor
@Builder

// Api Result
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
    } //end constructor

    // Parametreli Constructor (pmes)
    public ApiResult(String path, String message,String error, Integer status) {
        this.path = path;
        this.message = message;
        this.error = error;
        this.status = status;
    } //end constructor

    // GETTER AND SETTER
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Map<String, String> getValidationErrors() {
        return validationErrors;
    }

    public void setValidationErrors(Map<String, String> validationErrors) {
        this.validationErrors = validationErrors;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
} //end class ApiResult
