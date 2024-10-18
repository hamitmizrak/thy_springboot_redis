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
@Entity
@Table(name = "products")
// Order(N) - Customer(1)
// Order(N) - Product(M)
public class ProductEntity extends AuditingAwareBaseEntity {

    // FIELD
    // ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ORDER NAME
    @Column(name = "name")
    private String name;

    // ORDER (CODE)
    @Column(name = "price")
    private String price;

    // CREATED DATE
    @Column(name = "system_created_Date")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date systemCreatedDate;

    ////////////////////////////////////////////////////////////////////////////////////////////
    // RELATION
    // Order(N) - Customer(1)
    @ManyToOne(cascade = CascadeType.ALL, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private CustomerEntity customerEntity;

    // RELATION
    // Order(N) - Product(M)
    @ManyToMany(mappedBy = "productEntityList",fetch = FetchType.LAZY)
    private List<OrderEntity> orderEntityList;
} //end AddressEntity
