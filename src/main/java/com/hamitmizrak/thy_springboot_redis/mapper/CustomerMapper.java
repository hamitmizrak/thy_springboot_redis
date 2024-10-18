package com.hamitmizrak.thy_springboot_redis.mapper;

import com.hamitmizrak.thy_springboot_redis.business.dto.CustomerDto;
import com.hamitmizrak.thy_springboot_redis.data.entity.CustomerEntity;

import java.util.stream.Collectors;

// Entity => Dto
public class CustomerMapper {

    // Entity => Dto
    public static CustomerDto CustomerEntityToDto(CustomerEntity customerEntity) {
        // CustomerDto Instance
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(customerEntity.getId());
        customerDto.setName(customerEntity.getName());
        customerDto.setSurname(customerEntity.getSurname());
        customerDto.setCreatedDate(customerEntity.getCreatedDate());

        // DİKKAT: Composition (Customer(1) Adres(1))
        if (customerEntity.getAddressCustomerEntity() != null) {
            customerDto.setAddressDto(AddressMapper.AddressEntityToDto(customerEntity.getAddressCustomerEntity()));
        } else {
            System.out.println("Customer(1) Adres(1) Customer Composition Adress null");
        }

        // DİKKAT: Composition (Customer(1) Order(N))
        if(customerEntity.getOrderCustomerEntityList()!=null){
            customerDto.setOrderDtoList(
                    customerEntity
                            .getOrderCustomerEntityList()
                            .stream()
                            .map(OrderMapper::OrderEntityToDto)
                            .collect(Collectors.toList())
            );
        }else{
            System.out.println("(Customer(1) Order(N) Customer Composition Order null");
        }

        return customerDto;
    } //end CustomerEntityToDto


    // Dto => Entity
    public static CustomerEntity CustomerDtoToEntity(CustomerDto customerDto) {
        // CustomerEntity Instance
        CustomerEntity customerEntity = new CustomerEntity();

        // AddressEntity Set
        // NOT: Embedded için ID ve DATE dışında bıraktım.
        customerEntity.setId(customerDto.getId());
        customerEntity.setName(customerDto.getName());
        customerEntity.setSurname(customerDto.getSurname());
        customerEntity.setCreatedDate(customerDto.getCreatedDate());

        // DİKKAT: Composition (Customer(1) Adres(1))
        if(customerDto.getAddressDto() != null) {
            customerEntity.setAddressCustomerEntity(AddressMapper.AddressDtoToEntity(customerDto.getAddressDto()));
        }

        // DİKKAT: Composition (Customer(1) Order(N))
        if(customerDto.getOrderDtoList()!=null){
            customerEntity.setOrderCustomerEntityList(
                    customerDto
                            .getOrderDtoList()
                            .stream()
                            .map(OrderMapper::OrderDtoToEntity)
                            .collect(Collectors.toList()));
        }else{
            System.out.println("(Customer(1) Order(N) Customer Composition Order null");
        }
        return customerEntity;
    } // CustomerDtoToEntity

} //end CustomerMapper
