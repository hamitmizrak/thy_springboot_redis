package com.hamitmizrak.thy_springboot_redis.controller.api.impl;

import com.hamitmizrak.thy_springboot_redis.business.dto.ProductDto;
import com.hamitmizrak.thy_springboot_redis.business.services.IProductService;
import com.hamitmizrak.thy_springboot_redis.controller.api.IProductApi;
import com.hamitmizrak.thy_springboot_redis.data.entity.ProductEntity;
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
@RequestMapping("/api/product")
public class ProductApiImpl implements IProductApi<ProductDto> {

    // INJECTION
    private final IProductService iProductService;

    /////////////////////////////////////////////////////////////////////////////
    // Error
    private ApiResult apiResult;

    /////////////////////////////////////////////////////////////////////////////

    // CREATE API (product)
    // http://localhost:4444/api/product/create
    @PostMapping("/create")
    @Override
    public ResponseEntity<?> productApiCreate(@Valid @RequestBody ProductDto productDto) {
        ProductDto productDtoCreate = (ProductDto) iProductService.productServiceCreate(productDto);

        // return new ResponseEntity<>(productDtoCreate, HttpStatus.OK);
        // return new ResponseEntity<>(productDtoCreate, HttpStatus.CREATED);
        // return  ResponseEntity.status( HttpStatus.OK).body(productDtoCreate);
        // return  ResponseEntity.status(200).body(productDtoCreate);
        // return  ResponseEntity.ok().body(productDtoCreate);
        // return  ResponseEntity.ok(productDtoCreate);
        return new ResponseEntity<>(productDtoCreate, HttpStatus.CREATED);
    }

    // LIST API (product)
    // http://localhost:4444/api/product/list
    //@GetMapping("/list")
    //@GetMapping(value = "/list")
    @GetMapping(value = "/list")
    @Override
    public ResponseEntity<List<ProductDto>> productApiList() {
        return ResponseEntity.ok(iProductService.productServiceList());
    }

    // FIND  API (product)
    // http://localhost:4444/api/product/find/%20  nullpointerexception => BadRequest
    // http://localhost:4444/api/product/find/0    => BadRequest
    // http://localhost:4444/api/product/find/1
    @GetMapping({"/find/", "/find/{id}"})
    @Override
    public ResponseEntity<?> productApiFindById(@PathVariable(name = "id", required = false) Long id) {
        if (id == null) {
            throw new NullPointerException("ID Değeri Null olamaz");
        } else if (id <= 0) {
            throw new _400_BadRequestException("ID 0 olamaz, Geçersiz bir ID değeridir");
        }
        return ResponseEntity.status(HttpStatus.OK).body(iProductService.productServiceFindById(id));
    }

    // UPDATE  API (product)
    // http://localhost:4444/api/product/update/1
    @PutMapping("/update/{id}")
    @Override
    public ResponseEntity<?> productApiUpdateFindById(@PathVariable("id") Long id, @Valid @RequestBody ProductDto productDto) {
        return ResponseEntity.status(200).body(iProductService.productServiceUpdateById(id, productDto));
    }

    // DELETE  API (product)
    // http://localhost:4444/api/product/delete/%20
    // http://localhost:4444/api/product/delete/0
    // http://localhost:4444/api/product/delete/-1
    // http://localhost:4444/api/product/delete/44
    // http://localhost:4444/api/product/delete/1
    @DeleteMapping("/delete/{id}")
    @Override
    public ResponseEntity<?> productApiDeleteFindById(@PathVariable(name = "id", required = false) Long id) {
        if (id == null) {
            log.error("API: 404  NOT FOUND");
            return ResponseEntity.notFound().build();
        } else if (id == 0) {
            log.error("API: 400  BAD REQUEST");
            // String path, String message,String error, Integer status
            apiResult = new ApiResult("http://localhost:4444/api/product/delete/0", "Kötü istek atıldı", "BadRequest", 400);
            return ResponseEntity.badRequest().body(apiResult);
        } else if (id < 0) {
            log.error("API: 401  UNAUTHROZED");
            apiResult = ApiResult.builder()
                    .path("http://localhost:4444/api/product/delete/-1")
                    .message("Yetkisiz Giriş")
                    .error("Unauthorized")
                    //.status(HttpStatus.UNAUTHORIZED.value())
                    .status(400)
                    .build();
            return ResponseEntity.status(401).body(apiResult);
        }
        return ResponseEntity.ok(iProductService.productServiceDeleteById(id));
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    // PAGINATION
    // http://localhost:4444/api/product/pagination?currentPage=0&pageSize=3  => 1.sayfada bana 3 tane veri göster
    // currentPage=0 demek ilk sayfa demektir
    @Override
    //@GetMapping("/pagination")
    @GetMapping(value = "/pagination")
    public ResponseEntity<Page<ProductEntity>> productServicePagination(
            @RequestParam(name = "currentPage", required = false, defaultValue = "0") int currentPage,
            @RequestParam(name = "pageSize", required = false, defaultValue = "3") int pageSize
    ) {
        return ResponseEntity.ok(iProductService.productServicePagination(currentPage, pageSize));
    }

    // SORTING BELLI SUTUNA GÖRE
    // http://localhost:4444/api/product/sorting?sortBy=price
    // http://localhost:4444/api/product/sorting?sortBy=name
    @Override
    @GetMapping("/sorting")
    public ResponseEntity<List<?>> productApiAllSortedBy(
            @RequestParam(name = "sortBy", required = false, defaultValue = "price") String sortedBy
    ) {
        return ResponseEntity.ok(iProductService.productServiceAllSortedBy(sortedBy));
    }

    // SORTING ASC
    // http://localhost:4444/api/product/sorting/city/asc
    // Default Olarak Addres Entityden Şehire göre Küçükten Büyüğe Doğru Sıralama
    @Override
    @GetMapping("/sorting/city/asc")
    public ResponseEntity<List<?>> productApiAllSortedByCityAsc() {
        return ResponseEntity.ok(iProductService.productServiceAllSortedByCityAsc());
    }

    // SORTING DESC
    // http://localhost:4444/api/product/sorting/city/desc
    // Default Olarak Addres Entityden Şehire göre Büyükten Küçüğe Doğru Sıralama
    @Override
    @GetMapping("/sorting/city/desc")
    public ResponseEntity<List<?>> productApiAllSortedByCityDesc() {
        return ResponseEntity.ok(iProductService.productServiceAllSortedByCityDesc());
    }
} //end productApiImpl
