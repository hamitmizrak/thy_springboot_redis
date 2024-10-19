package com.hamitmizrak.thy_springboot_redis.controller.api.impl;

import com.hamitmizrak.thy_springboot_redis.business.services.IApplicationInformationService;
import com.hamitmizrak.thy_springboot_redis.controller.api.IApplicationInformationApi;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// LOMBOK
@RequiredArgsConstructor
@Log4j2

// API: Dış dünyaya açılan kapı
@RestController
@RequestMapping("/api/application")
public class ApplicationInformationApiImpl implements IApplicationInformationApi {

    // INJECTION
    private final IApplicationInformationService iApplicationInformationService;

    // HEADER
    // http://localhost:4444/api/application/header
    @Override
    @GetMapping(value = "/header")
    public ResponseEntity<?> headerApi(HttpServletRequest request) {
        return ResponseEntity.ok(iApplicationInformationService.headerService(request));
    }

    // APP INFORMATION
    // http://localhost:4444/api/application/information
    @Override
    @GetMapping(value = "/information")
    public ResponseEntity<?> appInformationApi(HttpServletRequest request, HttpServletResponse response) {
        return ResponseEntity.ok(iApplicationInformationService.appInformationService(request, response));
    }
} //end ApplicationInformationApiImpl
