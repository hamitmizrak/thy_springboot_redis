package com.hamitmizrak.thy_springboot_redis.controller.api.impl;

import com.hamitmizrak.thy_springboot_redis.business.dto.AddressDto;
import com.hamitmizrak.thy_springboot_redis.business.services.IAddressService;
import com.hamitmizrak.thy_springboot_redis.controller.api.IAddressApi;
import com.hamitmizrak.thy_springboot_redis.data.entity.AddressEntity;
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
@RequestMapping("/api/address")
public class AddressApiImpl implements IAddressApi<AddressDto> {

    // INJECTION
    private final IAddressService iAddressService;

    /////////////////////////////////////////////////////////////////////////////
    // Error
    private ApiResult apiResult;

    /////////////////////////////////////////////////////////////////////////////

    // CREATE API (Address)
    // http://localhost:4444/api/address/create
    @PostMapping("/create")
    @Override
    public ResponseEntity<?> addressApiCreate(@Valid @RequestBody AddressDto addressDto) {
        AddressDto addressDtoCreate = (AddressDto) iAddressService.addressServiceCreate(addressDto);

        // return new ResponseEntity<>(addressDtoCreate, HttpStatus.OK);
        // return new ResponseEntity<>(addressDtoCreate, HttpStatus.CREATED);
        // return  ResponseEntity.status( HttpStatus.OK).body(addressDtoCreate);
        // return  ResponseEntity.status(200).body(addressDtoCreate);
        // return  ResponseEntity.ok().body(addressDtoCreate);
        // return  ResponseEntity.ok(addressDtoCreate);
        return new ResponseEntity<>(addressDtoCreate, HttpStatus.CREATED);
    }

    // LIST API (Address)
    // http://localhost:4444/api/address/list
    //@GetMapping("/list")
    //@GetMapping(value = "/list")
    @GetMapping(
            name = "/addres_list_name",
            value = "/list"
            //path = "/list",
            //params = "active=true", //isteğinde belirli bir query parametresinin bulunmasını zorunlu kılar. api/address/list?active=true
            //consumes = "application/json", //sadece bu formatta gelen verileri kabul eder. Örnek: application/json,application/xml,application/x-www-form-urlencoded
            //produces = "application/xml", //API'nin döneceği formatı tanımlar.
            //headers = "X-API-VERSION=1" //İstek başlığında (header) belirli bir bilginin bulunmasını zorunlu kılar.
    )

    // LIST AddressAPI
    @Override
    public ResponseEntity<List<AddressDto>> addressApiList() {
        return ResponseEntity.ok(iAddressService.addressServiceList());
    }

    // FIND  API (Address)
    // http://localhost:4444/api/address/find/%20  nullpointerexception => BadRequest
    // http://localhost:4444/api/address/find/0    => BadRequest
    // http://localhost:4444/api/address/find/1
    @GetMapping({"/find/", "/find/{id}"})
    @Override
    public ResponseEntity<?> addressApiFindById(@PathVariable(name = "id", required = false) Long id) {
        if (id == null) {
            throw new NullPointerException("ID Değeri Null olamaz");
        } else if (id <= 0) {
            throw new _400_BadRequestException("ID 0 olamaz, Geçersiz bir ID değeridir");
        }
        return ResponseEntity.status(HttpStatus.OK).body(iAddressService.addressServiceFindById(id));
    }

    // UPDATE  API (Address)
    // http://localhost:4444/api/address/update/1
    @PutMapping("/update/{id}")
    @Override
    public ResponseEntity<?> addressApiUpdateFindById(@PathVariable("id") Long id, @Valid @RequestBody AddressDto addressDto) {
        return ResponseEntity.status(200).body(iAddressService.addressServiceUpdateById(id, addressDto));
    }

    // DELETE  API (Address)
    // http://localhost:4444/api/address/delete/%20
    // http://localhost:4444/api/address/delete/0
    // http://localhost:4444/api/address/delete/-1
    // http://localhost:4444/api/address/delete/44
    // http://localhost:4444/api/address/delete/1
    @DeleteMapping("/delete/{id}")
    @Override
    public ResponseEntity<?> addressApiDeleteFindById(@PathVariable(name = "id", required = false) Long id) {
        if (id == null) {
            log.error("API: 404  NOT FOUND");
            return ResponseEntity.notFound().build();
        } else if (id == 0) {
            log.error("API: 400  BAD REQUEST");
            // String path, String message,String error, Integer status
            apiResult = new ApiResult("http://localhost:4444/api/address/delete/0", "Kötü istek atıldı", "BadRequest", 400);
            return ResponseEntity.badRequest().body(apiResult);
        } else if (id < 0) {
            log.error("API: 401  UNAUTHROZED");
            apiResult = ApiResult.builder()
                    .path("http://localhost:4444/api/address/delete/-1")
                    .message("Yetkisiz Giriş")
                    .error("Unauthorized")
                    //.status(HttpStatus.UNAUTHORIZED.value())
                    .status(400)
                    .build();
            return ResponseEntity.status(401).body(apiResult);
        }
        return ResponseEntity.ok(iAddressService.addressServiceDeleteById(id));
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    // ROOT (Spring MVC)
    // http://localhost:4444/api/address
    // http://localhost:4444/api/address/index
    @GetMapping("/index")
    @Override
    public ResponseEntity<?> getRoot() {
        return ResponseEntity.ok("index");
    }

    // PAGINATION
    // http://localhost:4444/api/address/pagination?currentPage=0&pageSize=3  => 1.sayfada bana 3 tane veri göster
    // currentPage=0 demek ilk sayfa demektir
    @Override
    //@GetMapping("/pagination")
    @GetMapping(value = "/pagination")
    public ResponseEntity<Page<AddressEntity>> addressServicePagination(
            @RequestParam(name = "currentPage", required = false, defaultValue = "0") int currentPage,
            @RequestParam(name = "pageSize", required = false, defaultValue = "3") int pageSize
    ) {
        return ResponseEntity.ok(iAddressService.addressServicePagination(currentPage, pageSize));
    }

    // SORTING BELLI SUTUNA GÖRE
    // http://localhost:4444/api/address/sorting?sortBy=addressDetails.street
    // http://localhost:4444/api/address/sorting?sortBy=addressDetails.state
    // http://localhost:4444/api/address/sorting?sortBy=addressDetails.city
    // Adres Entityden belirli sutununa göre Sıramalama
    // NOT: Embeddable Entity verileri aldığımdan dolayı aşağıdaki gibi çağırmak zorundayım
    /*
    addressDetails.doorNumber, addressDetails.street, paddressDetails.avenue, addressDetails.city, addressDetails.zipCode
    addressDetails.addressQrCode, addressDetails.state, addressDetails.description
     */
    @Override
    @GetMapping("/sorting")
    public ResponseEntity<List<?>> addressApiAllSortedBy(
            @RequestParam(name = "sortBy", required = false, defaultValue = "addressDetails.state") String sortedBy
    ) {
        return ResponseEntity.ok(iAddressService.addressServiceAllSortedBy(sortedBy));
    }

    // SORTING ASC
    // http://localhost:4444/api/address/sorting/city/asc
    // Default Olarak Addres Entityden Şehire göre Küçükten Büyüğe Doğru Sıralama
    @Override
    @GetMapping("/sorting/city/asc")
    public ResponseEntity<List<?>> addressApiAllSortedByCityAsc() {
        return ResponseEntity.ok(iAddressService.addressServiceAllSortedByCityAsc());
    }

    // SORTING DESC
    // http://localhost:4444/api/address/sorting/city/desc
    // Default Olarak Addres Entityden Şehire göre Büyükten Küçüğe Doğru Sıralama
    @Override
    @GetMapping("/sorting/city/desc")
    public ResponseEntity<List<?>> addressApiAllSortedByCityDesc() {
        return ResponseEntity.ok(iAddressService.addressServiceAllSortedByCityDesc());
    }

} //end AddressApiImpl
