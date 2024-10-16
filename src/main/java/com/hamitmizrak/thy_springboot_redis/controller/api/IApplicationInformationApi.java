package com.hamitmizrak.thy_springboot_redis.controller.api;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface IApplicationInformationApi {

    // Header Information
    public ResponseEntity<?>  headerApi(Map<String,String> headers);

    // App Information
    public ResponseEntity<?>  appInformationApi(HttpServletRequest request, HttpServletResponse response);
}
