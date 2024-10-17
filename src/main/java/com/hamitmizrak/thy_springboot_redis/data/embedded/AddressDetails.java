package com.hamitmizrak.thy_springboot_redis.data.embedded;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Lob;
import lombok.Getter;
import lombok.Setter;

// LOMBOK
@Getter
@Setter

// Gömülü Sınıf: Entity Classlar'da karmaşıklığı azaltmak için kullanılır
@Embeddable
public class AddressDetails {

    // DOOR NUMBER
    @Column(name = "door_number")
    private String doorNumber;

    // STREET (SOKAK)
    @Column(name = "street")
    private String street;

    // AVENUE (Cadde)
    @Column(name = "avenue")
    private String avenue;

    // CITY
    @Column(name = "city")
    private String city;

    // ZIP CODE
    @Column(name = "zip_code")
    private String zipCode;

    // ZIP CODE
    @Column(name = "address_qr_code")
    private String addressQrCode;

    // STATE
    @Column(name = "state")
    private String state;

    // DESCRIPTON
    @Column(name = "description")
    @Lob
    private String description;

}
