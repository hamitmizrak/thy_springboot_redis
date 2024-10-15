package com.hamitmizrak.thy_springboot_redis.controller.api;

import com.hamitmizrak.thy_springboot_redis.business.dto.AddressDto;
import com.hamitmizrak.thy_springboot_redis.data.entity.AddressEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

// D: Dto
public interface IAddressApi <D>  extends IProfileHeaderApi {

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

    //////////////////////////////////////////////////////////////////////////////////
    // LIST PAGINATION
    ResponseEntity<Page<AddressEntity>> addressApiPagination(int currentPage, int pageSize);

    // LIST PAGEABLE
    ResponseEntity<Page<AddressDto>> addressApiPageable(Pageable pageable, D d);

} //end IAddressApi
