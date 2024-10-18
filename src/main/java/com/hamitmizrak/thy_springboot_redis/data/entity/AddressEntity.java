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
@Entity(name = "AddressEntity") // name: JPQL sorgularında kullanılacak varlık adını özelleştirmek için kullanılır.
@Table(name = "addresses") // name: database tablo adı
// Address(1) - Customer(1)
public class AddressEntity extends AuditingAwareBaseEntity {

    // FIELD
    // ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Embedded
    @Embedded
    private AddressDetails addressDetails;

    // CREATED DATE
    @Column(name = "system_created_Date")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date systemCreatedDate;

    // RELATION
    @OneToOne(mappedBy = "addressCustomerEntity",fetch = FetchType.LAZY)
    private CustomerEntity customerAddressEntity;

} //end AddressEntity
