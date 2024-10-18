package com.hamitmizrak.thy_springboot_redis.runner;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

// @Component : Spring Framework'un bir anatasyonudur. Spring Frameworkun bir bileşen sınıfı olduğunu gösteriyor
// Spring'in otomatik bu sınıfı bir bean nesnesi olarak tanımasına izin veriyoruz.

// LOMBOK
@RequiredArgsConstructor
@Log4j2

@Component
public class DataLoading_2_ implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Order Product");
        log.info("Order Product");
    }
}
