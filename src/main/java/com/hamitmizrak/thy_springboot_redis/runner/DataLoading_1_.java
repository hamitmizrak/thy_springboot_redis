package com.hamitmizrak.thy_springboot_redis.runner;

import com.hamitmizrak.thy_springboot_redis.business.dto.AddressDto;
import com.hamitmizrak.thy_springboot_redis.business.dto.CustomerDto;
import com.hamitmizrak.thy_springboot_redis.business.dto.OrderDto;
import com.hamitmizrak.thy_springboot_redis.business.dto.ProductDto;
import com.hamitmizrak.thy_springboot_redis.business.services.IAddressService;
import com.hamitmizrak.thy_springboot_redis.business.services.ICustomerService;
import com.hamitmizrak.thy_springboot_redis.business.services.IOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

// LOMBOK
@RequiredArgsConstructor
@Log4j2

@Configuration
public class DataLoading_1_ {

    // Injection
    private final IAddressService iAddressService;
    private final ICustomerService iCustomerService;
    private final IOrderService iOrderService;

    // Database Veri Ekleme

    // 1 TANE ADRESS EKLE
    private AddressDto addressSave(){
        System.out.println("###############################################");
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

    // 1 TANE MÜŞTERİ EKLE
    private CustomerDto customerSave(){
        System.out.println("###############################################");
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
        // NOT: Customer İçin Order'daki Composition kullanacağım için pasif yaptım
        //CustomerDto savedCustomer= (CustomerDto) iCustomerService.customerServiceCreate(customerDto);
        return customerDto;
    }

    // 2 TANE ÜRÜN EKLE
    private ProductDto[] productSave(){
        System.out.println("###############################################");
        log.info("Product Verileri Kaydediliyor");
        System.out.println("Product Verileri Kaydediliyor");

        // Dizi Tanımla
        ProductDto[] productDtoArray = new ProductDto[2];

        // Ürün 1
        ProductDto productDto1 = new ProductDto();
        productDto1.setName("Masaüstü");
        productDto1.setPrice("10.000");

        // Ürün 1
        ProductDto productDto2 = new ProductDto();
        productDto2.setName("Laptop");
        productDto2.setPrice("15.000");

        // Diziye Ekle
        productDtoArray[0]=productDto1;
        productDtoArray[1]=productDto2;

        return productDtoArray;
    }

    // SİPARİŞ EKLE
    private OrderDto orderSave(){
        System.out.println("###############################################");
        log.info("Order Verileri Kaydediliyor");
        System.out.println("Order Verileri Kaydediliyor");

        // Order Instance
        OrderDto orderDto = new OrderDto();
        orderDto.setName("Kahvaltı");
        orderDto.setCode("code-1");
        // Composition (Müşteri Ekle ve zaten Müşteride Adress vardı)
        orderDto.setCustomerDto(customerSave());
        orderDto.setProductDtoList(Arrays.asList(productSave()[0],productSave()[1]));
        System.out.println(orderDto);

        // Database Kaydetmek
        OrderDto orderDtoSaved = (OrderDto) iOrderService.orderServiceCreate(orderDto);
        System.out.println(orderDtoSaved);

        return orderDto;
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
             //CustomerDto customerDto = customerSave();

            // Order Kaydet
            orderSave();
        };
    } //end getDataSet

} //end class DataLoading
