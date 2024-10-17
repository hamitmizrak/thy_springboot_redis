package com.hamitmizrak.thy_springboot_redis.controller.api.impl;


import com.hamitmizrak.thy_springboot_redis.business.dto.OrderDto;
import com.hamitmizrak.thy_springboot_redis.business.services.IOrderService;
import com.hamitmizrak.thy_springboot_redis.controller.api.IOrderApi;
import com.hamitmizrak.thy_springboot_redis.data.entity.OrderEntity;
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
@RequestMapping("/api/order")
public class OrderApiImpl implements IOrderApi<OrderDto> {

    // INJECTION
    private final IOrderService iOrderService;

    /////////////////////////////////////////////////////////////////////////////
    // Error
    private ApiResult apiResult;

    /////////////////////////////////////////////////////////////////////////////

    // CREATE API (order)
    // http://localhost:4444/api/order/create
    @PostMapping("/create")
    @Override
    public ResponseEntity<?> orderApiCreate(@Valid @RequestBody OrderDto orderDto) {
        OrderDto orderDtoCreate = (OrderDto) iOrderService.orderServiceCreate(orderDto);

        // return new ResponseEntity<>(orderDtoCreate, HttpStatus.OK);
        // return new ResponseEntity<>(orderDtoCreate, HttpStatus.CREATED);
        // return  ResponseEntity.status( HttpStatus.OK).body(orderDtoCreate);
        // return  ResponseEntity.status(200).body(orderDtoCreate);
        // return  ResponseEntity.ok().body(orderDtoCreate);
        // return  ResponseEntity.ok(orderDtoCreate);
        return new ResponseEntity<>(orderDtoCreate, HttpStatus.CREATED);
    }

    // LIST API (order)
    // http://localhost:4444/api/order/list
    //@GetMapping("/list")
    //@GetMapping(value = "/list")
    @GetMapping(value = "/list")
    @Override
    public ResponseEntity<List<OrderDto>> orderApiList() {
        return ResponseEntity.ok(iOrderService.orderServiceList());
    }

    // FIND  API (order)
    // http://localhost:4444/api/order/find/%20  nullpointerexception => BadRequest
    // http://localhost:4444/api/order/find/0    => BadRequest
    // http://localhost:4444/api/order/find/1
    @GetMapping({"/find/", "/find/{id}"})
    @Override
    public ResponseEntity<?> orderApiFindById(@PathVariable(name = "id", required = false) Long id) {
        if (id == null) {
            throw new NullPointerException("ID Değeri Null olamaz");
        } else if (id <= 0) {
            throw new _400_BadRequestException("ID 0 olamaz, Geçersiz bir ID değeridir");
        }
        return ResponseEntity.status(HttpStatus.OK).body(iOrderService.orderServiceFindById(id));
    }

    // UPDATE  API (order)
    // http://localhost:4444/api/order/update/1
    @PutMapping("/update/{id}")
    @Override
    public ResponseEntity<?> orderApiUpdateFindById(@PathVariable("id") Long id, @Valid @RequestBody OrderDto orderDto) {
        return ResponseEntity.status(200).body(iOrderService.orderServiceUpdateById(id, orderDto));
    }

    // DELETE  API (order)
    // http://localhost:4444/api/order/delete/%20
    // http://localhost:4444/api/order/delete/0
    // http://localhost:4444/api/order/delete/-1
    // http://localhost:4444/api/order/delete/44
    // http://localhost:4444/api/order/delete/1
    @DeleteMapping("/delete/{id}")
    @Override
    public ResponseEntity<?> orderApiDeleteFindById(@PathVariable(name = "id", required = false) Long id) {
        if (id == null) {
            log.error("API: 404  NOT FOUND");
            return ResponseEntity.notFound().build();
        } else if (id == 0) {
            log.error("API: 400  BAD REQUEST");
            // String path, String message,String error, Integer status
            apiResult = new ApiResult("http://localhost:4444/api/order/delete/0", "Kötü istek atıldı", "BadRequest", 400);
            return ResponseEntity.badRequest().body(apiResult);
        } else if (id < 0) {
            log.error("API: 401  UNAUTHROZED");
            apiResult = ApiResult.builder()
                    .path("http://localhost:4444/api/order/delete/-1")
                    .message("Yetkisiz Giriş")
                    .error("Unauthorized")
                    //.status(HttpStatus.UNAUTHORIZED.value())
                    .status(400)
                    .build();
            return ResponseEntity.status(401).body(apiResult);
        }
        return ResponseEntity.ok(iOrderService.orderServiceDeleteById(id));
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////

    // PAGINATION
    // http://localhost:4444/api/order/pagination?currentPage=0&pageSize=3  => 1.sayfada bana 3 tane veri göster
    // currentPage=0 demek ilk sayfa demektir
    @Override
    //@GetMapping("/pagination")
    @GetMapping(value = "/pagination")
    public ResponseEntity<Page<OrderEntity>> orderServicePagination(
            @RequestParam(name = "currentPage", required = false, defaultValue = "0") int currentPage,
            @RequestParam(name = "pageSize", required = false, defaultValue = "3") int pageSize
    ) {
        return ResponseEntity.ok(iOrderService.orderServicePagination(currentPage, pageSize));
    }

    // SORTING BELLI SUTUNA GÖRE
    // http://localhost:4444/api/order/sorting?sortBy=price
    // http://localhost:4444/api/order/sorting?sortBy=name
    @Override
    @GetMapping("/sorting")
    public ResponseEntity<List<?>> orderApiAllSortedBy(
            @RequestParam(name = "sortBy", required = false, defaultValue = "price") String sortedBy
    ) {
        return ResponseEntity.ok(iOrderService.orderServiceAllSortedBy(sortedBy));
    }

    // SORTING ASC
    // http://localhost:4444/api/order/sorting/city/asc
    // Default Olarak Addres Entityden Şehire göre Küçükten Büyüğe Doğru Sıralama
    @Override
    @GetMapping("/sorting/city/asc")
    public ResponseEntity<List<?>> orderApiAllSortedByCityAsc() {
        return ResponseEntity.ok(iOrderService.orderServiceAllSortedByCityAsc());
    }

    // SORTING DESC
    // http://localhost:4444/api/order/sorting/city/desc
    // Default Olarak Addres Entityden Şehire göre Büyükten Küçüğe Doğru Sıralama
    @Override
    @GetMapping("/sorting/city/desc")
    public ResponseEntity<List<?>> orderApiAllSortedByCityDesc() {
        return ResponseEntity.ok(iOrderService.orderServiceAllSortedByCityDesc());
    }

} //end orderApiImpl
