package com.hamitmizrak.thy_springboot_redis.data.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Table(name="address")
public class AddressEntity {

    // FIELD
    // ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    // STATE
    @Column(name = "state")
    private String state;

    // DESCRIPTON
    @Column(name = "description")
    private String description;

    // CREATED DATE
    @Column(name = "created_Date")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    // RELATION

} //end AddressEntity
