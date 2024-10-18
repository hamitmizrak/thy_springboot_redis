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
@Entity(name="ProductEntity") // name: JPQL sorgularında kullanılacak varlık adını özelleştirmek için kullanılır.
@Table(name = "products") // name: database tablo adı
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
    // Order(N) - Product(M)
    @ManyToMany(mappedBy = "productOrderEntityList",fetch = FetchType.LAZY)
    private List<OrderEntity> orderProductEntityList;
} //end AddressEntity
