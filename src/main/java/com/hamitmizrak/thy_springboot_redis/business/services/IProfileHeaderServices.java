package com.hamitmizrak.thy_springboot_redis.business.services;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Map;

public interface IProfileHeaderServices {

    // Header Information
    public void headerService(Map<String, String> headers);

    // App Information
    public String appInformationService(HttpServletRequest request, HttpServletResponse response);

}
