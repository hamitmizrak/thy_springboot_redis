package com.hamitmizrak.thy_springboot_redis.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.Map;

// LOMBOK
@Data
@Builder
@AllArgsConstructor

// spring Frameworkta gelen Error'ları kendimize göre yakalamak
// (Jackson: objeyi json'a çevirir)
// Eğer sistemde null değer varsa backentte gönderme
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResult {

    // sem pvc
    private String path;
    private String message;
    private String error;
    private Integer status;
    private Date createdDate=new Date(System.currentTimeMillis());
    private Map<String,String> validationErrors;

    // parametresiz constructor
    public ApiResult() {
    }

    // parametreli constructor pms
    public ApiResult(String path, String message, Integer status) {
        this.path = path;
        this.message = message;
        this.status = status;
    }

    // parametreli constructor pmes
    public ApiResult(String path, String message, String error, Integer status) {
        this.path = path;
        this.message = message;
        this.error = error;
        this.status = status;
    }
} //end class
