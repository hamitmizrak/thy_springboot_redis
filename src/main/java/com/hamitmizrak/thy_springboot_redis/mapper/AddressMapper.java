package com.hamitmizrak.thy_springboot_redis.mapper;

import com.hamitmizrak.thy_springboot_redis.business.dto.AddressDto;
import com.hamitmizrak.thy_springboot_redis.data.entity.AddressEntity;

// Entity => Dto
public class AddressMapper {

    // Entity => Dto
    public static AddressDto AddressEntityToDto(AddressEntity addressEntity) {
        // AddressDto Instance
        AddressDto addressDto = new AddressDto();

        // AddressDto Set
        addressDto.setId(addressEntity.getId());
        addressDto.setDoorNumber(addressEntity.getDoorNumber());
        addressDto.setStreet(addressEntity.getStreet());
        addressDto.setAvenue(addressEntity.getAvenue());
        addressDto.setCity(addressEntity.getCity());
        addressDto.setZipCode(addressEntity.getZipCode());
        addressDto.setState(addressEntity.getState());
        addressDto.setDescription(addressEntity.getDescription());
        addressDto.setCreatedDate(addressEntity.getCreatedDate());
        return addressDto;
    } //end AddressEntityToDto

    // Dto => Entity
    public static AddressEntity AddressDtoToEntity(AddressDto addressDto) {
        // AddressEntity Instance
        AddressEntity addressEntity = new AddressEntity();

        // AddressEntity Set
        addressEntity.setId(addressDto.getId());
        addressEntity.setDoorNumber(addressDto.getDoorNumber());
        addressEntity.setStreet(addressDto.getStreet());
        addressEntity.setAvenue(addressDto.getAvenue());
        addressEntity.setCity(addressDto.getCity());
        addressEntity.setZipCode(addressDto.getZipCode());
        addressEntity.setState(addressDto.getState());
        addressEntity.setDescription(addressDto.getDescription());
        addressEntity.setCreatedDate(addressDto.getCreatedDate());
        return addressEntity;
    } // AddressDtoToEntity

} //end AddressMapper
