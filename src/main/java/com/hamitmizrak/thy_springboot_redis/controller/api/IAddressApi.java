package com.hamitmizrak.thy_springboot_redis.controller.api;

import com.hamitmizrak.thy_springboot_redis.data.entity.AddressEntity;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;

// D: Dto
public interface IAddressApi<D> {

    // CREATE
    public ResponseEntity<?> addressApiCreate(D d);

    // LIST
    public ResponseEntity<List<D>> addressApiList();

    // FIND BY ID
    public ResponseEntity<?> addressApiFindById(Long id);

    // UPDATE
    public ResponseEntity<?> addressApiUpdateFindById(Long id, D d);

    // DELETE
    public ResponseEntity<?> addressApiDeleteFindById(Long id);

    // ROOT
    public ResponseEntity<?> getRoot();

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // PAGINATION
    public ResponseEntity<Page<AddressEntity>> addressServicePagination(int currentPage, int pageSize);

    // SORTING
    // Adres Entityden belirli sutununa göre Sıramalama
    public ResponseEntity<List<?>> addressApiAllSortedBy(String sortedBy);

    // SORTING ASC
    // Default Olarak Addres Entityden Şehire göre Küçükten Büyüğe Doğru Sıralama
    public ResponseEntity<List<?>> addressApiAllSortedByCityAsc();

    // SORTING DESC
    // Default Olarak Addres Entityden Şehire göre Büyükten Küçüğe Doğru Sıralama
    public ResponseEntity<List<?>> addressApiAllSortedByCityDesc();

} //end IAddressApi
