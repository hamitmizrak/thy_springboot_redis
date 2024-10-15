package com.hamitmizrak.thy_springboot_redis.runner;

import com.hamitmizrak.thy_springboot_redis.business.services.IAddressService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// LOMBOK
@RequiredArgsConstructor
@Log4j2


@Configuration
public class DataLoading {

    // Injection
    private final IAddressService iAddressService;


    // Loading
    private void getData() {
        log.info("Veriler Yükleniyor.");
        System.out.println("Veriler Yükleniyor.");
    }

    // CommandLineRunner Method
    @Bean
    public CommandLineRunner getDataSet() {
        return args -> {
            getData();
        };
    } //end getDataSet

} //end class DataLoading
