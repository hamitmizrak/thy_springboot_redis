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

    // Database Address Ekleme
    private AddressDto addressSave(){
        log.info("Address Verileri Kaydediliyor");
        System.out.println("Address Verileri Kaydediliyor");
        // AddressDto
        AddressDto addressDto = new AddressDto();
        addressDto.setDoorNumber("44");
        addressDto.setStreet("Kernek");
        addressDto.setAvenue("Yeşilyurt");
        addressDto.setZipCode("44044");
        addressDto.setCity("Malatya");
        addressDto.setState("Türkiye");
        addressDto.setDescription("Tanımlama");
        AddressDto savedAddress= (AddressDto) iAddressService.addressServiceCreate(addressDto);
        return savedAddress;
    }


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
            // Adress Kaydet
            AddressDto addressDto=  addressSave();
        };
    } //end getDataSet

} //end class DataLoading
