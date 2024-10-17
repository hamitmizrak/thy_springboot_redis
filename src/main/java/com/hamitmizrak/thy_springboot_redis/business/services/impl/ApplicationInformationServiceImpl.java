package com.hamitmizrak.thy_springboot_redis.business.services.impl;

import com.hamitmizrak.thy_springboot_redis.business.services.IApplicationInformationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

import java.util.List;

// Asıl iş yükünü yapan Bean
@Service
public class ApplicationInformationServiceImpl implements IApplicationInformationService {

    // HEADER
    @Override
    public List<String> headerService(HttpServletRequest request) {
        return List.of();
    }

    // APP INFORMATION
    @Override
    public List<String> appInformationService(HttpServletRequest request, HttpServletResponse response) {
        return List.of();
    }

} // end ApplicationInformationServiceImpl
