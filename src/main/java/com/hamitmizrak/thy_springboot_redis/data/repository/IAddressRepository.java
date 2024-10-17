package com.hamitmizrak.thy_springboot_redis.data.repository;

import com.hamitmizrak.thy_springboot_redis.data.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IAddressRepository extends JpaRepository<AddressEntity, Long> {
    // Delivered Query

    // QR Koda Göre arama yapsın.
    // NOT: Embeddable yapmadan önceki hali
    //Optional<AddressEntity> findByAddressQrCode(String qrCode);
    //Embeddable yapıldıktan sonraki Hali (Embeddable: AddressDetails_)
    Optional<AddressEntity> findByAddressDetails_AddressQrCode(String qrCode);

    // JPQL

    // Native
}
