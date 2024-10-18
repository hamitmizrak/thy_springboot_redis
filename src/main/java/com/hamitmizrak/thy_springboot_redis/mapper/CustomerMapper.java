package com.hamitmizrak.thy_springboot_redis.mapper;

import com.hamitmizrak.thy_springboot_redis.business.dto.CustomerDto;
import com.hamitmizrak.thy_springboot_redis.data.entity.CustomerEntity;

// Entity => Dto
public class CustomerMapper {

    // Entity => Dto
    public static CustomerDto CustomerEntityToDto(CustomerEntity customerEntity) {
        // CustomerDto Instance
        CustomerDto customerDto = new CustomerDto();
        //customerDto.setId(customerEntity.getId());
        customerDto.setName(customerEntity.getName());
        customerDto.setSurname(customerEntity.getSurname());
        customerDto.setCreatedDate(customerEntity.getCreatedDate());

        // DİKKAT: COMPOSITION (Customer(1) Adres(1))
        /*
        if (customerEntity.getAddressEntity() != null) {
            customerDto.setAddressDto(AddressMapper.AddressEntityToDto(customerEntity.getAddressEntity()));
        }
        */
        customerDto.setAddressDto(AddressMapper.AddressEntityToDto(customerEntity.getAddressEntity()));

        // DİKKAT: COMPOSITION (Customer(1) Order(N))

        return customerDto;
    } //end CustomerEntityToDto


    // Dto => Entity
    public static CustomerEntity CustomerDtoToEntity(CustomerDto customerDto) {
        // CustomerEntity Instance
        CustomerEntity customerEntity = new CustomerEntity();

        // AddressEntity Set
        // NOT: Embedded için ID ve DATE dışında bıraktım.
        //customerEntity.setId(customerDto.getId());
        customerEntity.setName(customerDto.getName());
        customerEntity.setSurname(customerDto.getSurname());
        customerEntity.setCreatedDate(customerDto.getCreatedDate());

        // DİKKAT: COMPOSITION (Customer(1) Adres(1))
        /*
        if(customerDto.getAddressDto() != null) {
            customerEntity.setAddressEntity(AddressMapper.AddressDtoToEntity(customerDto.getAddressDto()));
        }
        */
        customerEntity.setAddressEntity(AddressMapper.AddressDtoToEntity(customerDto.getAddressDto()));

        // DİKKAT: COMPOSITION (Customer(1) Order(N))
        return customerEntity;
    } // CustomerDtoToEntity

} //end CustomerMapper
