package com.hamitmizrak.thy_springboot_redis.data.repository;

import com.hamitmizrak.thy_springboot_redis.data.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// CrudRepository
// PagingAndSortingRepository
// JpaRepository (Tercih edilen çünkü hem CrudRepoistory hemde PagingAndSortingRepository metotları dahildir.)

@Repository
public interface IOrderRepository extends JpaRepository<OrderEntity, Long> {
    // Delivered Query

    // JPQL

    // Native
}
