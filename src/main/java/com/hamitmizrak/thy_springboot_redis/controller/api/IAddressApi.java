package com.hamitmizrak.thy_springboot_redis.controller.api;

import org.springframework.http.ResponseEntity;

import java.util.List;

// D: Dto
public interface IAddressApi <D> {

    // CREATE
    ResponseEntity<?> addressApiCreate(D d);

    // LIST
    ResponseEntity<List<D>> addressApiList();

    // FIND BY ID
    ResponseEntity<?> addressApiFindById(Long id);

    // UPDATE
    ResponseEntity<?> addressApiUpdateFindById(Long id, D d);

    // DELETE
    ResponseEntity<?> addressApiDeleteFindById(Long id);
} //end IAddressApi
