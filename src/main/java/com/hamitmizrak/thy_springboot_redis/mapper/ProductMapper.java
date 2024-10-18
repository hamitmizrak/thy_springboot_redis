package com.hamitmizrak.thy_springboot_redis.mapper;

import com.hamitmizrak.thy_springboot_redis.business.dto.ProductDto;
import com.hamitmizrak.thy_springboot_redis.data.entity.ProductEntity;

import java.util.List;
import java.util.stream.Collectors;

// Entity => Dto
public class ProductMapper {

    // Entity => Dto
    public static ProductDto ProductEntityToDto(ProductEntity productEntity) {
        // ProductDto Instance
        ProductDto productDto = new ProductDto();
        productDto.setName(productEntity.getName());
        productDto.setPrice(productEntity.getPrice());
        return productDto;
    } //end ProductEntityToDto

    // Dto => Entity
    public static ProductEntity ProductDtoToEntity(ProductDto productDto) {
        // ProductEntity Instance
        ProductEntity productEntity = new ProductEntity();
        productEntity.setName(productDto.getName());
        productEntity.setPrice(productDto.getPrice());
        return productEntity;
    } // ProductDtoToEntity

    // ProductDto'yu Listesi Product Entity Dönüştürmek
    public static List<ProductEntity> ProductDtoListToEntityList(List<ProductDto> productDtoList) {
        // Her bir ProductDto Product Entity Dönüştür
        return productDtoList.stream().map(ProductMapper::ProductDtoToEntity).collect(Collectors.toList());
    }

} //end CProductMapper
