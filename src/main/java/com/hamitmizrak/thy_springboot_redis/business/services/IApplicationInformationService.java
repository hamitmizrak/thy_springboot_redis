package com.hamitmizrak.thy_springboot_redis.business.services;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;


public interface IApplicationInformationService {

    // Header Information
    public List<String> headerService(HttpServletRequest request);

    // App Information
    public List<String> appInformationService(HttpServletRequest request, HttpServletResponse response);
}
