package com.hamitmizrak.thy_springboot_redis.mapper;

import com.hamitmizrak.thy_springboot_redis.business.dto.OrderDto;
import com.hamitmizrak.thy_springboot_redis.data.entity.OrderEntity;

// Entity => Dto
public class OrderMapper {

    // Entity => Dto
    public static OrderDto OrderEntityToDto(OrderEntity orderEntity) {

        // OrderDto Instance
        OrderDto orderDto = new OrderDto();
        orderDto.setId(orderEntity.getId());
        orderDto.setName(orderEntity.getName());
        orderDto.setPrice(orderEntity.getPrice());
        orderDto.setCode(orderEntity.getCode());
        orderDto.setCreatedDate(orderEntity.getCreatedDate());

        // DİKKAT: COMPOSITION (Order(N) - Customer(1))
        if(orderEntity.getCustomerEntity()!=null){
            orderDto.setCustomerDto(CustomerMapper.CustomerEntityToDto(orderEntity.getCustomerEntity()));
        }

        // DİKKAT: COMPOSITION (Order(N) - Product(M))

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

        // DİKKAT: COMPOSITION (Order(N) - Customer(1))
        if(orderDto.getCustomerDto()!=null){
            orderEntity.setCustomerEntity(CustomerMapper.CustomerDtoToEntity(orderDto.getCustomerDto()));
        }

        // DİKKAT: COMPOSITION (Order(N) - Product(M))

        return orderEntity;
    } // CustomerDtoToEntity

} //end CustomerMapper
