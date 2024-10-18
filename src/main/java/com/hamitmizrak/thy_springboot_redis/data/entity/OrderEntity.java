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
@Entity(name="OrderEntity") // name: JPQL sorgularında kullanılacak varlık adını özelleştirmek için kullanılır.
@Table(name = "orders") // name: database tablo adı
// Order(N) - Customer(1)
// Order(N) - Product(M)
public class OrderEntity extends AuditingAwareBaseEntity {

    // FIELD
    // ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ORDER NAME
    @Column(name = "name")
    private String name;

    // ORDER (CODE)
    @Column(name = "code")
    private String code;

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
    private CustomerEntity customerOrderEntity;

    // RELATION
    // Order(N) - Product(M)
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name="order_product",
            joinColumns = @JoinColumn(name="order_id"),
            inverseJoinColumns = @JoinColumn(name="product_id")
    )
    private List<ProductEntity> productOrderEntityList;

} //end AddressEntity
