package com.hamitmizrak.thy_springboot_redis.mapper;

import com.hamitmizrak.thy_springboot_redis.business.dto.AddressDto;
import com.hamitmizrak.thy_springboot_redis.data.embedded.AddressDetails;
import com.hamitmizrak.thy_springboot_redis.data.entity.AddressEntity;

// Entity => Dto
public class AddressMapper {

    // Entity => Dto
    public static AddressDto AddressEntityToDto(AddressEntity addressEntity) {
        // AddressDto Instance
        AddressDto addressDto = new AddressDto();

        // AddressDto Set
        addressDto.setId(addressEntity.getId());

        // AddressDetails alanlarını AddressDto'ya aktar
        if (addressEntity.getAddressDetails() != null) {
            AddressDetails addressDetails = addressEntity.getAddressDetails();
            addressDto.setDoorNumber(addressDetails.getDoorNumber());
            addressDto.setStreet(addressDetails.getStreet());
            addressDto.setAvenue(addressDetails.getAvenue());
            addressDto.setCity(addressDetails.getCity());
            addressDto.setZipCode(addressDetails.getZipCode());
            addressDto.setAddressQrCode(addressDetails.getAddressQrCode());
            addressDto.setState(addressDetails.getState());
            addressDto.setDescription(addressDetails.getDescription());
        }

        // Tarih alanı
        addressDto.setSystemCreatedDate(addressEntity.getSystemCreatedDate());
        return addressDto;
    } // end AddressEntityToDto

    // Dto => Entity
    public static AddressEntity AddressDtoToEntity(AddressDto addressDto) {
        // AddressEntity Instance
        AddressEntity addressEntity = new AddressEntity();

        // AddressEntity Set
        addressEntity.setId(addressDto.getId());

        // AddressDetails alanlarını doldur
        AddressDetails addressDetails = new AddressDetails();
        addressDetails.setDoorNumber(addressDto.getDoorNumber());
        addressDetails.setStreet(addressDto.getStreet());
        addressDetails.setAvenue(addressDto.getAvenue());
        addressDetails.setCity(addressDto.getCity());
        addressDetails.setZipCode(addressDto.getZipCode());
        addressDetails.setAddressQrCode(addressDto.getAddressQrCode());
        addressDetails.setState(addressDto.getState());
        addressDetails.setDescription(addressDto.getDescription());

        // AddressDetails'i AddressEntity'ye ekle
        addressEntity.setAddressDetails(addressDetails);

        // Tarih alanı
        addressEntity.setSystemCreatedDate(addressDto.getSystemCreatedDate());
        return addressEntity;
    } // end AddressDtoToEntity

}