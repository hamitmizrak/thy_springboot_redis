package com.hamitmizrak.thy_springboot_redis.runner;

import com.hamitmizrak.thy_springboot_redis.business.dto.AddressDto;
import com.hamitmizrak.thy_springboot_redis.business.dto.CustomerDto;
import com.hamitmizrak.thy_springboot_redis.business.services.IAddressService;
import com.hamitmizrak.thy_springboot_redis.business.services.ICustomerService;
import com.hamitmizrak.thy_springboot_redis.exception._404_NotFoundException;
import com.hamitmizrak.thy_springboot_redis.mapper.AddressMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

// LOMBOK
@RequiredArgsConstructor
@Log4j2

@Configuration
public class DataLoadingAddressCustomer {

    // Injection
    private final IAddressService iAddressService;
    private final ICustomerService iCustomerService;

    // Database Address Ekleme
    // 1.ADRESS
    private AddressDto addressSave(){
        log.info("Address Verileri Kaydediliyor");
        System.out.println("Address Verileri Kaydediliyor");
        // AddressDto
        AddressDto addressDto = new AddressDto();
        addressDto.setDoorNumber("44");
        addressDto.setStreet("Kernek");
        addressDto.setAvenue("Yeşilyurt");
        addressDto.setZipCode("44044");
        addressDto.setAddressQrCode(UUID.randomUUID().toString());
        addressDto.setCity("Malatya");
        addressDto.setState("Türkiye");
        addressDto.setDescription("Tanımlama");
        // NOT: Address için Customerda Composition kullanacağım için pasif yaptım
        //AddressDto savedAddress= (AddressDto) iAddressService.addressServiceCreate(addressDto);
        return addressDto;
    }

    // 1.MÜŞTERİ
    private CustomerDto customerSave(){
        log.info("Customer Verileri Kaydediliyor");
        System.out.println("Customer Verileri Kaydediliyor");

        // Adres Dto
        AddressDto addressDto = addressSave();

        // CustomerDto
        CustomerDto customerDto = new CustomerDto();
        customerDto.setName("Hamit");
        customerDto.setSurname("Mızrak");

        // Composition
        customerDto.setAddressDto(addressDto);
        CustomerDto savedCustomer= (CustomerDto) iCustomerService.customerServiceCreate(customerDto);
        return savedCustomer;
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
            // AddressDto addressDto=  addressSave();

            // Customer Kaydet
            CustomerDto customerDto = customerSave();
        };
    } //end getDataSet

} //end class DataLoading
