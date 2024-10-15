package com.hamitmizrak.thy_springboot_redis.controller.api.impl;

import com.hamitmizrak.thy_springboot_redis.business.dto.AddressDto;
import com.hamitmizrak.thy_springboot_redis.business.services.IAddressService;
import com.hamitmizrak.thy_springboot_redis.controller.api.IAddressApi;
import com.hamitmizrak.thy_springboot_redis.data.entity.AddressEntity;
import com.hamitmizrak.thy_springboot_redis.error.ApiResult;
import com.hamitmizrak.thy_springboot_redis.exception._400_BadRequestException;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.Cacheable;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

// LOMBOK
@RequiredArgsConstructor
@Log4j2

// API: Dış dünyaya açılan kapı
@RestController
@RequestMapping("/api/address")
public class AddressApiImpl implements IAddressApi<AddressDto> {

    // INJECTION
    private final IAddressService iAddressService;

    ///////////////////////////////////////////////////////////
    // ERROR
    private ApiResult apiResult;

    // @PostConstruct
    @PostConstruct
    public void springData() {
        apiResult = new ApiResult();
    }

    // CREATE API (Address)
    // ENTPOINT: http://localhost:4444/api/address
    @PostMapping
    @Override
    public ResponseEntity<?> addressApiCreate(@Valid @RequestBody AddressDto addressDto) {
        /*
        return new ResponseEntity<>(blogDto, HttpStatus.OK);
        return  ResponseEntity.status(HttpStatus.OK).body(blogDto);
        return  ResponseEntity.status(200).body(blogDto);
        return  ResponseEntity.ok().body(blogDto);

        // ApiResult apiResult=new ApiResult(200,PATH,"created Employee");
        // return ResponseEntity.ok(apiResult);
        */
        AddressDto addressDtoCreate= (AddressDto) iAddressService.addressServiceCreate(addressDto);
        return new ResponseEntity<>(addressDtoCreate, HttpStatus.CREATED);
    }

    // LIST API (Address)
    // ENTPOINT: http://localhost:4444/api/address/list
    // ENTPOINT: http://localhost:4444/api/address/list?active=true
    //@Cacheable(value = "cacheBlogList")  // Dikkat: bunu eklediğinde veriler değişikler göreyebilirsin
    @GetMapping(
            //name = "/addres_list_name",
            value = "/list"
            //path = "/list2"
            //params = "active=true",
            //consumes = "application/json",
            //produces = "application/json",
            //headers = "X-API-VERSION=1"
    )
    @Override
    public ResponseEntity<List<AddressDto>> addressApiList() {
        return ResponseEntity.status(HttpStatus.OK).body(iAddressService.addressServiceList());
    }

    // FIND  API (Address)
    // ENTPOINT: http://localhost:4444/api/address/1
    // http://localhost:4444/api/address/0    => BadRequest
    // http://localhost:4444/api/address/%20  => nullpointerexception => BadRequest
    @GetMapping("/{id}")
    @Override
    public ResponseEntity<?> addressApiFindById(@PathVariable(name="id",required = false) Long id) {
        // http://localhost:4444/api/address/%20  => null
        // http://localhost:4444/api/address/-1   => badrequest
        if (id == null) {
            throw new NullPointerException("ID değeri null olamaz");
        }
        else if (id <= 0) {
            throw new _400_BadRequestException("ID 0 olamaz, geçersiz bir ID değeri.");
        }

        return ResponseEntity.status(200).body(iAddressService.addressServiceFindById(id));
    }

    // UPDATE  API (Address)
    // ENTPOINT: http://localhost:4444/api/address/1
    @PutMapping(value="/{id}")
    @Override
    public ResponseEntity<?> addressApiUpdateFindById(@PathVariable("id")  Long id, @Valid @RequestBody AddressDto addressDto) {
        return ResponseEntity.ok(iAddressService.addressServiceUpdateById(id, addressDto));
    }

    // DELETE  API (Address)
    // http://localhost:4444/api/address/%20
    // http://localhost:4444/api/address/0
    // http://localhost:4444/api/address/1
    @DeleteMapping({"/","/{id}"})
    @Override
    public ResponseEntity<?> addressApiDeleteFindById(@PathVariable(name="id", required = false) Long id) {
        if (id == null) {
            log.error("API => 404 NOT FOUND");
            return ResponseEntity.notFound().build();
        } else if (id == 0) {
            log.error("API => 400 BAD REQUEST");
            //String path, String message,String error, Integer status
            apiResult = new ApiResult("http://localhost:4444/api/address/1", "Kötü istek","bad request", 400);
            System.out.println(apiResult);
            //return ResponseEntity.badRequest().build();
            return ResponseEntity.badRequest().body(apiResult);
        } else if (id < 0) {
            log.error("API => 401 UNAUTHROZED");
            apiResult = ApiResult.builder()
                    .error("unAuthorized")
                    .message("Yetkisiz Giriş")
                    .path("localhost:4444/blog/api/v1/blog/-1")
                    .status(401)
                    .build();
            return ResponseEntity.status(401).body(apiResult);
        }
        return ResponseEntity.ok().body(iAddressService.addressServiceDeleteById(id));
    }

    ///////////////////////////////////////////////////////////////////////////////////
    // ROOT
    // http://localhost:4444/api/address/index
    @GetMapping("/index")
    public ResponseEntity<String> getRoot() {
        return ResponseEntity.ok("index");
    }


    // HEADERS
    // http://localhost:4444/api/address/header
    @Override
    @GetMapping("/header")
    public ResponseEntity<?>  headerApi(Map<String, String> headers) {
        iAddressService.headerService(headers);
        return ResponseEntity.ok("Header Data");
    }

    // APP INFORMATION
    // http://localhost:4444/api/address/app/information
    @Override
    @GetMapping("/app/information")
    public ResponseEntity<?> appInformationApi(HttpServletRequest request, HttpServletResponse response) {
        return ResponseEntity.ok(iAddressService.appInformationService(request,response));
    }


    ///////////////////////////////////////////////////////////////////////////////////
    // LIST PAGINATION
    // http://localhost:4444/api/address/pagination?currentPage=0&pageSize=3
    @Override
    @GetMapping(value = "/pagination")
    public ResponseEntity<Page<AddressEntity>> addressApiPagination(
            @RequestParam(name = "currentPage", required = false, defaultValue = "0") int currentPage,
            @RequestParam(name = "pageSize", required = false, defaultValue = "3") int pageSize) {
        return ResponseEntity.ok(iAddressService.addressServicePagination(currentPage,pageSize));
    }

    // LIST PAGINATION
    // spring.data.web.pageable.page-parameter=currentPage
    // spring.data.web.pageable.size-parameter=pageSize
    // http://localhost:4444/api/address/pageable?currentPage=0&pageSize=3
    // http://localhost:4444/api/address/pageable?page=0&size=3
    @GetMapping(value = "/pageable")
    @Override
    // Sistemdeki kullanıcı bilgisini almak:  @UserLoginSystem
    // (Pageable pageable, @UserLoginSystem BlogDto blogDto
    public ResponseEntity<Page<AddressDto>> addressApiPageable(Pageable pageable, AddressDto addressDto) {
        return ResponseEntity.ok(iAddressService.addressServicePageable(pageable, addressDto));
    }

} //end AddressApiImpl
