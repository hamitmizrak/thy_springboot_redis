package com.hamitmizrak.thy_springboot_redis.data.entity;

import com.hamitmizrak.thy_springboot_redis.audit.AuditingAwareBaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import java.util.Date;
import java.util.List;

// LOMBOK
@Setter
@Getter

// ENTITY
@Entity(name = "CustomerEntity") // name: JPQL sorgularında kullanılacak varlık adını özelleştirmek için kullanılır.
@Table(name = "customers") // name: database tablo adı
// Customer(1) - Address(1)
// Customer(1) - Order(N)
public class CustomerEntity extends AuditingAwareBaseEntity {

    // FIELD
    // ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // CUSTOMER NAME
    @Column(name = "name")
    private String name;

    // CUSTOMER SURNAME
    @Column(name = "surname")
    private String surname;

    // CREATED DATE
    @Column(name = "system_created_Date")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date systemCreatedDate;

    // RELATION
    // Customer(1) - Address(1) ADDRESS(FK)
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id",unique = true)
    private AddressEntity addressCustomerEntity;

    // RELATION
    // Customer(1) - Order(N)
    @OneToMany(mappedBy = "customerOrderEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY,orphanRemoval = true)
    private List<OrderEntity> orderCustomerEntityList;

} //end AddressEntity
