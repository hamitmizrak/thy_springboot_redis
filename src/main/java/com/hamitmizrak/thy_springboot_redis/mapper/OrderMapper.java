package com.hamitmizrak.thy_springboot_redis.mapper;

import com.hamitmizrak.thy_springboot_redis.business.dto.OrderDto;
import com.hamitmizrak.thy_springboot_redis.data.entity.OrderEntity;

import java.util.stream.Collectors;

// Entity => Dto
public class OrderMapper {

    // Entity => Dto
    public static OrderDto OrderEntityToDto(OrderEntity orderEntity) {

        // OrderDto Instance
        OrderDto orderDto = new OrderDto();
        orderDto.setId(orderEntity.getId());
        orderDto.setName(orderEntity.getName());
        orderDto.setCode(orderEntity.getCode());
        orderDto.setCreatedDate(orderEntity.getCreatedDate());

        // DİKKAT: Composition (Order(N) - Customer(1))
        if(orderEntity.getCustomerOrderEntity()!=null){
            orderDto.setCustomerDto(CustomerMapper.CustomerEntityToDto(orderEntity.getCustomerOrderEntity()));
        }

        // DİKKAT: Composition (Order(N) - Product(M))
        if(orderEntity.getProductOrderEntityList()!=null){
            orderDto.setProductDtoList(
                    orderEntity
                            .getProductOrderEntityList()
                            .stream()
                            .map(ProductMapper::ProductEntityToDto)
                             .collect(Collectors.toList())
            );
        }
        return orderDto;
    } //end CustomerEntityToDto

    // Dto => Entity
    public static OrderEntity OrderDtoToEntity(OrderDto orderDto) {
        // CustomerEntity Instance
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(orderDto.getId());
        orderEntity.setName(orderDto.getName());
        orderEntity.setCode(orderDto.getCode());

        // DİKKAT: Composition (Order(N) - Customer(1))
        if(orderDto.getCustomerDto()!=null){
            orderEntity.setCustomerOrderEntity(CustomerMapper.CustomerDtoToEntity(orderDto.getCustomerDto()));
        }

        // DİKKAT: Composition (Order(N) - Product(M))
        if(orderDto.getProductDtoList()!=null){
           orderEntity.setProductOrderEntityList(
                   orderDto.getProductDtoList()
                           .stream()
                           .map(ProductMapper::ProductDtoToEntity)
                           .collect(Collectors.toList())
           );
        }
        return orderEntity;
    } // OrderDtoToEntity

} //end OrderMapper
