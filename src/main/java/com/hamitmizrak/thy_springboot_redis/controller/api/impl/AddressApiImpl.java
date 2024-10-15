package com.hamitmizrak.thy_springboot_redis.controller.api.impl;

import com.hamitmizrak.thy_springboot_redis.business.dto.AddressDto;
import com.hamitmizrak.thy_springboot_redis.business.services.IAddressService;
import com.hamitmizrak.thy_springboot_redis.controller.api.IAddressApi;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// LOMBOK
@RequiredArgsConstructor
@Log4j2

// API: Dış dünyaya açılan kapı
@RestController
@RequestMapping("/api/address")
public class AddressApiImpl implements IAddressApi<AddressDto> {

    // INJECTION
    private final IAddressService iAddressService;

    // CREATE API (Address)
    // http://localhost:4444/api/address
    @PostMapping
    @Override
    public ResponseEntity<?> addressApiCreate(@Valid @RequestBody AddressDto addressDto) {
        AddressDto addressDtoCreate= (AddressDto) iAddressService.addressServiceCreate(addressDto);
        return new ResponseEntity<>(addressDtoCreate, HttpStatus.CREATED);
    }

    // LIST API (Address)
    // http://localhost:4444/api/address
    @GetMapping
    @Override
    public ResponseEntity<List<AddressDto>> addressApiList() {
        return ResponseEntity.ok(iAddressService.addressServiceList());
    }

    // FIND  API (Address)
    // http://localhost:4444/api/address/1
    // http://localhost:4444/api/address/0    => BadRequest
    // http://localhost:4444/api/address/%20  nullpointerexception => BadRequest
    @GetMapping("/{id}")
    @Override
    public ResponseEntity<?> addressApiFindById(@PathVariable(name="id",required = false) Long id) {
        return ResponseEntity.ok(iAddressService.addressServiceFindById(id));
    }

    // UPDATE  API (Address)
    // http://localhost:4444/api/address/1
    @PutMapping("/{id}")
    @Override
    public ResponseEntity<?> addressApiUpdateFindById(@PathVariable("id")  Long id, @Valid @RequestBody AddressDto addressDto) {
        return ResponseEntity.ok(iAddressService.addressServiceUpdateById(id, addressDto));
    }

    // DELETE  API (Address)
    // http://localhost:4444/api/address/1
    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<?> addressApiDeleteFindById(@PathVariable(name="id") Long id) {
        return ResponseEntity.ok(iAddressService.addressServiceDeleteById(id));
    }
} //end AddressApiImpl
