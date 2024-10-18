package com.hamitmizrak.thy_springboot_redis.controller.api;

import com.hamitmizrak.thy_springboot_redis.data.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;

// D: Dto
public interface IProductApi<D> {

    // CREATE
    public ResponseEntity<?> productApiCreate(D d);

    // LIST
    public ResponseEntity<List<D>> productApiList();

    // FIND BY ID
    public ResponseEntity<?> productApiFindById(Long id);

    // UPDATE
    public ResponseEntity<?> productApiUpdateFindById(Long id, D d);

    // DELETE
    public ResponseEntity<?> productApiDeleteFindById(Long id);

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // PAGINATION
    public ResponseEntity<Page<ProductEntity>> productServicePagination(int currentPage, int pageSize);

    // SORTING
    // Adres Entityden belirli sutununa göre Sıramalama
    public ResponseEntity<List<?>> productApiAllSortedBy(String sortedBy);

    // SORTING ASC
    // Default Olarak Addres Entityden Şehire göre Küçükten Büyüğe Doğru Sıralama
    public ResponseEntity<List<?>> productApiAllSortedByCityAsc();

    // SORTING DESC
    // Default Olarak Addres Entityden Şehire göre Büyükten Küçüğe Doğru Sıralama
    public ResponseEntity<List<?>> productApiAllSortedByCityDesc();

} //end IProductApi
