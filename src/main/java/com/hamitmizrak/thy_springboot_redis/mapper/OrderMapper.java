package com.hamitmizrak.thy_springboot_redis.mapper;

import com.hamitmizrak.thy_springboot_redis.business.dto.CustomerDto;
import com.hamitmizrak.thy_springboot_redis.business.dto.OrderDto;
import com.hamitmizrak.thy_springboot_redis.data.entity.CustomerEntity;
import com.hamitmizrak.thy_springboot_redis.data.entity.OrderEntity;

// Entity => Dto
public class OrderMapper {

    // Entity => Dto
    public static OrderDto OrderEntityToDto(OrderEntity orderEntity) {
        // CustomerDto Instance
        OrderDto orderDto = new OrderDto();
        orderDto.setId(orderEntity.getId());
        orderDto.setName(orderEntity.getName());
        orderDto.setPrice(orderEntity.getPrice());
        orderDto.setCode(orderEntity.getCode());
        orderDto.setCreatedDate(orderEntity.getCreatedDate());

        // DİKKAT: COMPOSITION (ORDER - CUSTOMER)
        if(orderEntity.getCustomerEntity()!=null){
            orderDto.setCustomerDto(CustomerMapper.CustomerEntityToDto(orderEntity.getCustomerEntity()));
        }

        // DİKKAT: COMPOSITION (ORDER - PRODUCT)
        return orderDto;
    } //end CustomerEntityToDto

    // Dto => Entity
    public static OrderEntity OrderDtoToEntity(OrderDto orderDto) {
        // CustomerEntity Instance
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(orderDto.getId());
        orderEntity.setName(orderDto.getName());
        orderEntity.setPrice(orderDto.getPrice());
        orderEntity.setCode(orderDto.getCode());

        // DİKKAT: COMPOSITION (ORDER - CUSTOMER)
        if(orderDto.getCustomerDto()!=null){
            orderEntity.setCustomerEntity(CustomerMapper.CustomerDtoToEntity(orderDto.getCustomerDto()));
        }

        // DİKKAT: COMPOSITION (ORDER - PRODUCT)
        return orderEntity;
    } // CustomerDtoToEntity

} //end CustomerMapper
