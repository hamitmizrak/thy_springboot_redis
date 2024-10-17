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

        // NOT: Embedded için ID ve DATE dışında bıraktım.
        addressDto.setId(addressEntity.getId());
        addressDto.setCreatedDate(addressEntity.getCreatedDate());

        // AddressDto Set
        if (addressEntity.getAddressDetails() != null) {
            // Embeddable : AddressDetails
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
        return addressDto;
    } //end AddressEntityToDto

    // Dto => Entity
    public static AddressEntity AddressDtoToEntity(AddressDto addressDto) {
        // AddressEntity Instance
        AddressEntity addressEntity = new AddressEntity();

        // AddressEntity Set
        // NOT: Embedded için ID ve DATE dışında bıraktım.
        addressEntity.setId(addressDto.getId());
        addressEntity.setCreatedDate(addressDto.getCreatedDate());

        // Embeddable AddressDetails Instance
        AddressDetails addressDetails=new AddressDetails();
        addressDetails.setDoorNumber(addressDto.getDoorNumber());
        addressDetails.setStreet(addressDto.getStreet());
        addressDetails.setAvenue(addressDto.getAvenue());
        addressDetails.setCity(addressDto.getCity());
        addressDetails.setZipCode(addressDto.getZipCode());
        addressDetails.setAddressQrCode(addressDto.getAddressQrCode());
        addressDetails.setState(addressDto.getState());
        addressDetails.setDescription(addressDto.getDescription());

        // AdressDetails'i AdressEntity Ekle
        addressEntity.setAddressDetails(addressDetails);

        return addressEntity;
    } // AddressDtoToEntity

} //end AddressMapper
