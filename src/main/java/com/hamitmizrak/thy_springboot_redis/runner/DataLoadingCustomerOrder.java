package com.hamitmizrak.thy_springboot_redis.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoadingCustomerOrder implements CommandLineRunner {

    //@Autowired
    //private ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {
        // Test verilerini ekleyelim
        // productRepository.save(new Product("Laptop", 1200.00));
        System.out.println("Deneme Customer Order");
    }
}
