package com.hamitmizrak.thy_springboot_redis.controller.api;

import com.hamitmizrak.thy_springboot_redis.data.entity.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import java.util.List;

// D: Dto
public interface IOrderApi<D> {

    // CREATE
    public ResponseEntity<?> orderApiCreate(D d);

    // LIST
    public ResponseEntity<List<D>> orderApiList();

    // FIND BY ID
    public ResponseEntity<?> orderApiFindById(Long id);

    // UPDATE
    public ResponseEntity<?> orderApiUpdateFindById(Long id, D d);

    // DELETE
    public ResponseEntity<?> orderApiDeleteFindById(Long id);

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // PAGINATION
    public ResponseEntity<Page<OrderEntity>> orderServicePagination(int currentPage, int pageSize);

    // SORTING
    // Adres Entityden belirli sutununa göre Sıramalama
    public ResponseEntity<List<?>> orderApiAllSortedBy(String sortedBy);

    // SORTING ASC
    // Default Olarak Addres Entityden Şehire göre Küçükten Büyüğe Doğru Sıralama
    public ResponseEntity<List<?>> orderApiAllSortedByCityAsc();

    // SORTING DESC
    // Default Olarak Addres Entityden Şehire göre Büyükten Küçüğe Doğru Sıralama
    public ResponseEntity<List<?>> orderApiAllSortedByCityDesc();

} //end IOrderApi
