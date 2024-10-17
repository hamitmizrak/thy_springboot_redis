package com.hamitmizrak.thy_springboot_redis.business.services.impl;

import com.hamitmizrak.thy_springboot_redis.business.services.IApplicationInformationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

// LOMBOK
@Log4j2

// Asıl iş yükünü yapan Bean
@Service
public class ApplicationInformationServiceImpl implements IApplicationInformationService {

    // HEADER
    @Override
    public List<String> headerService(HttpServletRequest request) {

        // Header List
        List<String> headersList = new ArrayList<String>();

        // Header Bilgilerini HttpServletten almak
        Enumeration<String> headerNames = request.getHeaderNames();
        // Eğer Header bilgisi varsa
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String headerValue = request.getHeader(headerName);
            headersList.add(headerName + ": " + headerValue);
        }
        return headersList;
    }

    // APP INFORMATION
    @Override
    public List<String> appInformationService(HttpServletRequest request, HttpServletResponse response) {

        // Application List
        List<String> infoList = new ArrayList<String>();

        // REQUEST
        // istek işlemlerindeni path örneğin: /api
        String URI = request.getRequestURI();

        // Sunucunu IP Adresini
        String LOCALHOST = request.getLocalAddr();

        // Http Metotlar (GET,POST,PUT,DELETE)
        String HTTP_METHOD= request.getMethod();

        // Query String parametreleri
        String QUERY_STRING = request.getQueryString();

        // İsteği Gönderen istemcinin IP Addresini
        String CLIENT_IP = request.getRemoteAddr();

        // İStemcinin Tarayıcı, uygulama Bilgileri
        String USER_AGENT= request.getHeader("User-Agent");

        // Server adı
        String SERVER_NAME = request.getServerName();

        // İstekle ilgili Session ID
        String SESSION_ID = request.getSession().getId();

        // RESPONSE
        int STATUS_CODE = response.getStatus();

        // Yanıt İçeriği Tipi
        String CONTENT_TYPE = request.getContentType();

        // Header Custome Bir Veri eklemek
       response.addHeader("Custom-Header","HeaderValue");

       // LISTEYE EKLEMEK (CLIENT)
        infoList.add("URI: "+URI);
        infoList.add("LOCALHOST: "+LOCALHOST);
        infoList.add("HTTP_METHOD: "+HTTP_METHOD);
        infoList.add("QUERY_STRING: "+QUERY_STRING);
        infoList.add("CLIENT_IP: "+CLIENT_IP);
        infoList.add("USER_AGENT: "+USER_AGENT);
        infoList.add("SERVER_NAME: "+SERVER_NAME);

        // LISTEYE EKLEMEK (RESPONSE)
        infoList.add("SESSION_ID: "+SESSION_ID);
        infoList.add("STATUS_CODE: "+STATUS_CODE);
        infoList.add("CONTENT_TYPE: "+CONTENT_TYPE);

        // ÇIKTI
        System.out.println(infoList);
        log.info(infoList);

        // ÇIKTI DÖNGÜ
        infoList.forEach(System.out::println);

        return infoList;
    }

} // end ApplicationInformationServiceImpl
