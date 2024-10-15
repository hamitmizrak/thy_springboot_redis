package com.hamitmizrak.thy_springboot_redis.runner;

import com.hamitmizrak.thy_springboot_redis.business.dto.AddressDto;
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
    private AddressDto saveAddress() {
        log.info("Address Veriler Yükleniyor.");
        System.out.println(" Address Veriler Yükleniyor.");

        // 1. Adres oluşturma ve kaydetme
        AddressDto address1 = new AddressDto();
        address1.setStreet("123 Elm Street");
        address1.setCity("New York");
        address1.setDoorNumber("44");
        address1.setStreet("Bostanbaşı sok");
        address1.setAvenue("yeşilyurt cad");
        address1.setCity("Malatya");
        address1.setZipCode("44");
        address1.setState("Türkiye");
        address1.setDescription("Tanım");
        AddressDto savedAddress = (AddressDto) iAddressService.addressServiceCreate(address1);  // Adresi kaydediyoruz
        return savedAddress;
    }

    // CommandLineRunner Method
    @Bean
    public CommandLineRunner getDataSet() {
        return args -> {
         AddressDto addressDto=   saveAddress();
        };
    } //end getDataSet

} //end class DataLoading
