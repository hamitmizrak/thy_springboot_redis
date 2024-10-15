package com.hamitmizrak.thy_springboot_redis.business.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

// LOMBOK
@Getter
@Setter

// Address(1) - Customer(1)
public class AddressDto implements Serializable {

    // SERILESTIRME
    public final static Long serialVersionUID = 1L;

    // FIELD
    private Long id;
    private String doorNumber;
    private String street;
    private String avenue;
    private String city;
    private String zipCode;
    private String state;
    private String description;
    private Date createdDate;

} //end AddressDto
