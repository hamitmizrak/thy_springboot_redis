package com.hamitmizrak.thy_springboot_redis.business.services.impl;

import com.hamitmizrak.thy_springboot_redis.business.services.IApplicationInformationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

import java.util.*;

// Asıl İş Yükünü yapan yer
@Service
public class AppService implements IApplicationInformationService {

    // HEADER
    @Override
    public List<String> headerService(HttpServletRequest request) {
        List<String> headersList = new ArrayList<>();

        // Header bilgilerini HttpServletRequest'ten alma
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String headerValue = request.getHeader(headerName);
            headersList.add("Header Name: " + headerName + " Header Value: " + headerValue);
        }

        // List<String> olarak header bilgilerini döndürme
        return headersList;
    }

    // APP INFORMATION
    @Override
    public List<String> appInformationService(HttpServletRequest request, HttpServletResponse response) {
        List<String> infoList = new ArrayList<>();

        // Request ile ilgili bilgileri alma

        // İstek yapılan URI'yi alır (örneğin, "/home" gibi)
        String URI = request.getRequestURI();

        // Sunucunun IP adresini alır (localhost için genellikle 127.0.0.1 olur)
        String LOCALHOST = request.getLocalAddr();

        // İstekle ilişkili session ID'yi alır
        String SESSION_ID = request.getSession().getId();

        // HTTP metodunu alır (GET, POST, PUT, DELETE vb.)
        String HTTP_METHOD = request.getMethod();

        // İstekle birlikte gelen query string parametrelerini alır (örneğin, "?id=123&name=abc")
        String QUERY_STRING = request.getQueryString();

        // İstemcinin tarayıcı veya uygulama bilgilerini alır (User-Agent başlığı)
        String USER_AGENT = request.getHeader("User-Agent");

        // İsteği gönderen istemcinin IP adresini alır
        String CLIENT_IP = request.getRemoteAddr();


        // Response ile ilgili bilgileri alma

        // Yanıt durum kodunu alır (örneğin, 200 OK, 404 Not Found)
        int STATUS_CODE = response.getStatus();

        // Yanıtın içerik tipini alır (örneğin, text/html, application/json)
        String CONTENT_TYPE = response.getContentType();

        // Yanıta özel bir başlık ekler (Custom header ekleme örneği)
        response.addHeader("Custom-Header", "HeaderValue");


        // Bilgileri listeye ekleme
        infoList.add("URI: " + URI);  // İstek yapılan URI'yi ekler
        infoList.add("LOCALHOST: " + LOCALHOST);  // Sunucunun IP adresini ekler
        infoList.add("SESSION_ID: " + SESSION_ID);  // Session ID bilgisini ekler
        infoList.add("HTTP_METHOD: " + HTTP_METHOD);  // HTTP metodunu ekler
        infoList.add("QUERY_STRING: " + (QUERY_STRING != null ? QUERY_STRING : "N/A"));  // Query string'i ekler (yoksa N/A yazar)
        infoList.add("USER_AGENT: " + USER_AGENT);  // Tarayıcı veya uygulama bilgilerini ekler
        infoList.add("CLIENT_IP: " + CLIENT_IP);  // İstemcinin IP adresini ekler

        // Response bilgilerini listeye ekleme
        infoList.add("STATUS_CODE: " + STATUS_CODE);  // Yanıt durum kodunu ekler
        infoList.add("CONTENT_TYPE: " + (CONTENT_TYPE != null ? CONTENT_TYPE : "N/A"));  // Yanıt içerik tipini ekler
        infoList.add("Custom Header: " + response.getHeader("Custom-Header"));  // Eklenen custom header bilgisini ekler

        // Bilgileri yazdırma
        infoList.forEach(System.out::println);  // Listeyi ekrana yazdırır

        return infoList;  // Listeyi döndürür
    }

}
