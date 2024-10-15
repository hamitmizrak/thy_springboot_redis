package com.hamitmizrak.thy_springboot_redis.bean;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperBean {

    @Bean
    public ModelMapper getModelMapperBeanMethod() {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper;
    }
}
