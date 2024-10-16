package com.hamitmizrak.thy_springboot_redis.data.entity;

import com.hamitmizrak.thy_springboot_redis.audit.AuditingAwareBaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

// LOMBOK
@Setter
@Getter

// ENTITY
@Entity
@Table(name="address")
public class AddressEntity  extends AuditingAwareBaseEntity {

    // FIELD
    // ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Embedded

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

    // CREATED DATE
    @Column(name = "system_created_Date")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date systemCreatedDate;

    // RELATION

} //end AddressEntity
