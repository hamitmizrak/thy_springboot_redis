package com.hamitmizrak.thy_springboot_redis.controller.api;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;

import java.util.Map;

// NOT: interface için önemli bilgiler
// 1-) interface extends ile başka bir interface ekleyebilirsin. =>
// public interface IProfileHeaderApp extends IModelMapperService

// 2-) interface abstract ekleyerek implements eden class bütün metotları eklemez. =>
// abstract public interface IProfileHeaderApp

public interface IProfileHeaderApi {

    //ROOT
    public ResponseEntity<String> getRoot();

    //Header Information
    public ResponseEntity<?> headerApi(Map<String, String> headers);

    //App Information
    public ResponseEntity<?> appInformationApi(HttpServletRequest request, HttpServletResponse response);

}
