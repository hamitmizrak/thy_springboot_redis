package com.hamitmizrak.thy_springboot_redis.data.entity;

import com.hamitmizrak.thy_springboot_redis.audit.AuditingAwareBaseEntity;
import com.hamitmizrak.thy_springboot_redis.data.embedded.AddressDetails;
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
    // Adres detaylarını içeren gömülü sınıfı kullan
    @Embedded
    private AddressDetails addressDetails;

    // CREATED DATE
    @Column(name = "system_created_Date")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date systemCreatedDate;

    // RELATION

} //end AddressEntity
