package com.hamitmizrak.thy_springboot_redis.mapper;

import com.hamitmizrak.thy_springboot_redis.business.dto.CustomerDto;
import com.hamitmizrak.thy_springboot_redis.data.entity.CustomerEntity;

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
        // DİKKAT: COMPOSITION
        customerDto.setAddressDto(AddressMapper.AddressEntityToDto(customerEntity.getAddressEntity()));
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
        // DİKKAT: COMPOSITON
        customerEntity.setAddressEntity(AddressMapper.AddressDtoToEntity(customerDto.getAddressDto()));
        return customerEntity;
    } // CustomerDtoToEntity

} //end CustomerMapper
