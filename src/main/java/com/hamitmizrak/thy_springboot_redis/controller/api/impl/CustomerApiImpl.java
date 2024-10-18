package com.hamitmizrak.thy_springboot_redis.controller.api.impl;

import com.hamitmizrak.thy_springboot_redis.business.dto.CustomerDto;
import com.hamitmizrak.thy_springboot_redis.business.services.ICustomerService;
import com.hamitmizrak.thy_springboot_redis.controller.api.ICustomerApi;
import com.hamitmizrak.thy_springboot_redis.data.entity.CustomerEntity;
import com.hamitmizrak.thy_springboot_redis.error.ApiResult;
import com.hamitmizrak.thy_springboot_redis.exception._400_BadRequestException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

// LOMBOK
@RequiredArgsConstructor
@Log4j2

// API: Dış dünyaya açılan kapı
@RestController
@RequestMapping("/api/customer")
public class CustomerApiImpl implements ICustomerApi<CustomerDto> {

    // INJECTION
    private final ICustomerService iCustomerService;

    /////////////////////////////////////////////////////////////////////////////
    // Error
    private ApiResult apiResult;

    /////////////////////////////////////////////////////////////////////////////
    // CREATE API (Customer)
    // http://localhost:4444/api/customer/create
    @PostMapping("/create")
    @Override
    public ResponseEntity<?> customerApiCreate(@Valid @RequestBody CustomerDto customerDto) {
        CustomerDto customerDtoCreate = (CustomerDto) iCustomerService.customerServiceCreate(customerDto);

        // return new ResponseEntity<>(customerDtoCreate, HttpStatus.OK);
        // return new ResponseEntity<>(customerDtoCreate, HttpStatus.CREATED);
        // return  ResponseEntity.status( HttpStatus.OK).body(customerDtoCreate);
        // return  ResponseEntity.status(200).body(customerDtoCreate);
        // return  ResponseEntity.ok().body(customerDtoCreate);
        // return  ResponseEntity.ok(customerDtoCreate);
        return new ResponseEntity<>(customerDtoCreate, HttpStatus.CREATED);
    }

    // LIST API (Customer)
    // http://localhost:4444/api/customer/list
    //@GetMapping("/list")
    //@GetMapping(value = "/list")
    @GetMapping(value = "/list")
    @Override
    public ResponseEntity<List<CustomerDto>> customerApiList() {
        return ResponseEntity.ok(iCustomerService.customerServiceList());
    }

    // FIND  API (Customer)
    // http://localhost:4444/api/customer/find/%20  nullpointerexception => BadRequest
    // http://localhost:4444/api/customer/find/0    => BadRequest
    // http://localhost:4444/api/customer/find/1
    @GetMapping({"/find/", "/find/{id}"})
    @Override
    public ResponseEntity<?> customerApiFindById(@PathVariable(name = "id", required = false) Long id) {
        if (id == null) {
            throw new NullPointerException("ID Değeri Null olamaz");
        } else if (id <= 0) {
            throw new _400_BadRequestException("ID 0 olamaz, Geçersiz bir ID değeridir");
        }
        return ResponseEntity.status(HttpStatus.OK).body(iCustomerService.customerServiceFindById(id));
    }

    // UPDATE  API (Customer)
    // http://localhost:4444/api/customer/update/1
    @PutMapping("/update/{id}")
    @Override
    public ResponseEntity<?> customerApiUpdateFindById(@PathVariable("id") Long id, @Valid @RequestBody CustomerDto customerDto) {
        return ResponseEntity.status(200).body(iCustomerService.customerServiceUpdateById(id, customerDto));
    }

    // DELETE  API (Customer)
    // http://localhost:4444/api/customer/delete/%20
    // http://localhost:4444/api/customer/delete/0
    // http://localhost:4444/api/customer/delete/-1
    // http://localhost:4444/api/customer/delete/44
    // http://localhost:4444/api/customer/delete/1
    @DeleteMapping("/delete/{id}")
    @Override
    public ResponseEntity<?> customerApiDeleteFindById(@PathVariable(name = "id", required = false) Long id) {
        if (id == null) {
            log.error("API: 404  NOT FOUND");
            return ResponseEntity.notFound().build();
        } else if (id == 0) {
            log.error("API: 400  BAD REQUEST");
            // String path, String message,String error, Integer status
            apiResult = new ApiResult("http://localhost:4444/api/customer/delete/0", "Kötü istek atıldı", "BadRequest", 400);
            return ResponseEntity.badRequest().body(apiResult);
        } else if (id < 0) {
            log.error("API: 401  UNAUTHROZED");
            apiResult = ApiResult.builder()
                    .path("http://localhost:4444/api/customer/delete/-1")
                    .message("Yetkisiz Giriş")
                    .error("Unauthorized")
                    //.status(HttpStatus.UNAUTHORIZED.value())
                    .status(400)
                    .build();
            return ResponseEntity.status(401).body(apiResult);
        }
        return ResponseEntity.ok(iCustomerService.customerServiceDeleteById(id));
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////

    // PAGINATION
    // http://localhost:4444/api/customer/pagination?currentPage=0&pageSize=3  => 1.sayfada bana 3 tane veri göster
    // currentPage=0 demek ilk sayfa demektir
    @Override
    //@GetMapping("/pagination")
    @GetMapping(value = "/pagination")
    public ResponseEntity<Page<CustomerEntity>> customerServicePagination(
            @RequestParam(name = "currentPage", required = false, defaultValue = "0") int currentPage,
            @RequestParam(name = "pageSize", required = false, defaultValue = "3") int pageSize
    ) {
        return ResponseEntity.ok(iCustomerService.customerServicePagination(currentPage, pageSize));
    }

    // SORTING BELLI SUTUNA GÖRE
    // http://localhost:4444/api/customer/sorting?sortBy=name
    // http://localhost:4444/api/customer/sorting?sortBy=surname
    @Override
    @GetMapping("/sorting")
    public ResponseEntity<List<?>> customerApiAllSortedBy(
            @RequestParam(name = "sortBy", required = false, defaultValue = "name") String sortedBy
    ) {
        return ResponseEntity.ok(iCustomerService.customerServiceAllSortedBy(sortedBy));
    }

    // SORTING ASC
    // http://localhost:4444/api/customer/sorting/city/asc
    // Default Olarak Addres Entityden Şehire göre Küçükten Büyüğe Doğru Sıralama
    @Override
    @GetMapping("/sorting/city/asc")
    public ResponseEntity<List<?>> customerApiAllSortedByCityAsc() {
        return ResponseEntity.ok(iCustomerService.customerServiceAllSortedByCityAsc());
    }

    // SORTING DESC
    // http://localhost:4444/api/customer/sorting/city/desc
    // Default Olarak Addres Entityden Şehire göre Büyükten Küçüğe Doğru Sıralama
    @Override
    @GetMapping("/sorting/city/desc")
    public ResponseEntity<List<?>> customerApiAllSortedByCityDesc() {
        return ResponseEntity.ok(iCustomerService.customerServiceAllSortedByCityDesc());
    }
} //end CustomerApiImpl
