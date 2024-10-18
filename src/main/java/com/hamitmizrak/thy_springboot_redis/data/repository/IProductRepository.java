package com.hamitmizrak.thy_springboot_redis.data.repository;

import com.hamitmizrak.thy_springboot_redis.data.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// CrudRepository
// PagingAndSortingRepository
// JpaRepository (Tercih edilen çünkü hem CrudRepoistory hemde PagingAndSortingRepository metotları dahildir.)

@Repository
public interface IProductRepository extends JpaRepository<ProductEntity, Long> {
    // Delivered Query

    // JPQL

    // Native
}
