package com.hamitmizrak.thy_springboot_redis.controller.api;

import com.hamitmizrak.thy_springboot_redis.data.entity.CustomerEntity;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;

// D: Dto
public interface ICustomerApi<D> {

    // CREATE
    public ResponseEntity<?> customerApiCreate(D d);

    // LIST
    public ResponseEntity<List<D>> customerApiList();

    // FIND BY ID
    public ResponseEntity<?> customerApiFindById(Long id);

    // UPDATE
    public ResponseEntity<?> customerApiUpdateFindById(Long id, D d);

    // DELETE
    public ResponseEntity<?> customerApiDeleteFindById(Long id);

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // PAGINATION
    public ResponseEntity<Page<CustomerEntity>> customerServicePagination(int currentPage, int pageSize);

    // SORTING
    // Adres Entityden belirli sutununa göre Sıramalama
    public ResponseEntity<List<?>> customerApiAllSortedBy(String sortedBy);

    // SORTING ASC
    // Default Olarak Addres Entityden Şehire göre Küçükten Büyüğe Doğru Sıralama
    public ResponseEntity<List<?>> customerApiAllSortedByCityAsc();

    // SORTING DESC
    // Default Olarak Addres Entityden Şehire göre Büyükten Küçüğe Doğru Sıralama
    public ResponseEntity<List<?>> customerApiAllSortedByCityDesc();

} //end ICustomerApi
