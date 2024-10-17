package com.hamitmizrak.thy_springboot_redis.business.services.impl;

import com.hamitmizrak.thy_springboot_redis.bean.ModelMapperBean;
import com.hamitmizrak.thy_springboot_redis.business.dto.AddressDto;
import com.hamitmizrak.thy_springboot_redis.business.services.IAddressService;
import com.hamitmizrak.thy_springboot_redis.data.embedded.AddressDetails;
import com.hamitmizrak.thy_springboot_redis.data.entity.AddressEntity;
import com.hamitmizrak.thy_springboot_redis.data.repository.IAddressRepository;
import com.hamitmizrak.thy_springboot_redis.exception._404_NotFoundException;
import com.hamitmizrak.thy_springboot_redis.mapper.AddressMapper;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

// LOMBOK
//@RequiredArgsConstructor
@Log4j2

// Asıl İş Yükünü yapan yer
@Service
public class AddressServiceImpl implements IAddressService<AddressDto, AddressEntity> {

    // INJECTION
    private final IAddressRepository iAddressRepository;
    private final ModelMapperBean modelMapperBean;

    @Autowired
    public AddressServiceImpl(IAddressRepository iAddressRepository, ModelMapperBean modelMapperBean) {
        log.error("AddressServiceImpl Constructor çağırdım");
        System.out.println("AddressServiceImpl Constructor çağırdım");
        this.iAddressRepository = iAddressRepository;
        this.modelMapperBean = modelMapperBean;
    }

    //////////////////////////////////////////////////////////////////////////////////
    // @PostConstruct
    @PostConstruct
    public void init() {
        log.error("AddressServiceImpl @PostConstructor çağırdım");
        System.out.println("AddressServiceImpl @PostConstructor  çağırdım");
        log.info("Address Verileri @PostConstructor veri ekleme");
        // AddressDto
        AddressDto addressDtoRecursive = null;
        for (int i = 1; i <= 10; i++) {
            // AddressDto
            addressDtoRecursive = new AddressDto();
            addressDtoRecursive.setDoorNumber("kapı " + i);
            addressDtoRecursive.setStreet("sokak " + i);
            addressDtoRecursive.setAvenue("cadde " + i);
            addressDtoRecursive.setZipCode("posta " + i);
            addressDtoRecursive.setAddressQrCode(UUID.randomUUID().toString());
            addressDtoRecursive.setCity("şehir " + i);
            addressDtoRecursive.setState("ülke " + i);
            addressDtoRecursive.setDescription("Tanımlama " + i);
            AddressEntity addressCreateEntity = dtoToEntity(addressDtoRecursive);
            addressCreateEntity = iAddressRepository.save(addressCreateEntity);
        }
    }

    //////////////////////////////////////////////////////////////////////////////////
    // MODEL MAPPER
    @Override
    public AddressDto entityToDto(AddressEntity addressEntity) {
        // 1.YOL
        // return modelMapperBean.getModelMapperBeanMethod().map(addressEntity, AddressDto.class);

        // 2.YOL
        return AddressMapper.AddressEntityToDto(addressEntity);
    }

    @Override
    public AddressEntity dtoToEntity(AddressDto addressDto) {
        // 1.YOL
        // return modelMapperBean.getModelMapperBeanMethod().map(addressDto, AddressEntity.class);

        // 2.YOL
        return AddressMapper.AddressDtoToEntity(addressDto);
    }

    ////////////////////////////////////////////////////////////////////////////////
    // CREATE (Address)
    @Transactional // Create, Update, Delete
    @Override
    public AddressDto addressServiceCreate(AddressDto addressDto) {
        AddressEntity addressCreateEntity = dtoToEntity(addressDto);
        addressCreateEntity = iAddressRepository.save(addressCreateEntity);
        return entityToDto(addressCreateEntity);
    }

    // LIST (Address)
    @Override
    public List<AddressDto> addressServiceList() {
        return iAddressRepository.findAll().stream().map(AddressMapper::AddressEntityToDto).collect(Collectors.toList());
    }

    // FIND  (Address)
    @Override
    public AddressDto addressServiceFindById(Long id) {
        return iAddressRepository.findById(id)
                .map(AddressMapper::AddressEntityToDto)
                .orElseThrow(() -> new _404_NotFoundException(id + " nolu Address bulunamadı"));
    }

    // UPDATE  (Address)
    @Transactional // Create, Update, Delete
    @Override
    public AddressDto addressServiceUpdateById(Long id, AddressDto addressDto) {
        // Önce Nesne var mı yokmu
        AddressEntity addressUpdateEntity = dtoToEntity(addressServiceFindById(id));

        // Bulunan Nesneyi Set
        // Embeddable eklediğim AddressDetails yapımızı üzerinden Entity set etmeliyim.
        AddressDetails addressDetails = new AddressDetails();
        addressDetails.setDoorNumber(addressDto.getDoorNumber());
        addressDetails.setStreet(addressDto.getStreet());
        addressDetails.setAvenue(addressDto.getAvenue());
        addressDetails.setCity(addressDto.getCity());
        addressDetails.setZipCode(addressDto.getZipCode());
        addressDetails.setAddressQrCode(addressDto.getAddressQrCode());
        addressDetails.setState(addressDto.getState());
        addressDetails.setDescription(addressDto.getDescription());
        // NOT: Embeddable için ID ve DATE dışınd abıraktım
        addressUpdateEntity.setCreatedDate(addressDto.getCreatedDate());
        addressUpdateEntity = iAddressRepository.save(addressUpdateEntity);
        return entityToDto(addressUpdateEntity);
    }

    // DELETE  (Address)
    @Transactional // Create, Update, Delete
    @Override
    public AddressDto addressServiceDeleteById(Long id) {
        // Önce Nesne var mı yokmu
        AddressEntity addressDeleteEntity = dtoToEntity(addressServiceFindById(id));
        iAddressRepository.delete(addressDeleteEntity);
        return entityToDto(addressDeleteEntity);
    }

    //////////////////////////////////////////////////////////////////////////////////////
    // PAGINATION
    // import org.springframework.data.domain.Page;
    // import org.springframework.data.domain.Pageable;
    @Override
    public Page<AddressEntity> addressServicePagination(int currentPage, int pageSize) {
        Pageable pageable = PageRequest.of(currentPage, pageSize);
        Page<AddressEntity> addressEntityPage = iAddressRepository.findAll(pageable);
        return addressEntityPage;
    }

    // SORTING BELLI SUTUNA GORE
    @Override
    public List<AddressEntity> addressServiceAllSortedBy(String sortedBy) {
        // NOT: JpaRepository'de findAll Sorting kullanabilirim.
        return iAddressRepository.findAll(Sort.by(Sort.Direction.ASC, sortedBy));
        //return iAddressRepository.findAll(Sort.by(Sort.Direction.DESC, sortedBy));
    }

    // SORTING CITY ASC
    // NOT: Embeddable Entity verileri aldığımdan dolayı aşağıdaki gibi çağırmak zorundayım
    /*
    addressDetails.doorNumber, addressDetails.street, paddressDetails.avenue, addressDetails.city, addressDetails.zipCode
    addressDetails.addressQrCode, addressDetails.state, addressDetails.description
     */
    @Override
    public List<AddressEntity> addressServiceAllSortedByCityAsc() {
        return iAddressRepository.findAll(Sort.by(Sort.Direction.ASC, "addressDetails.city"));
    }

    // SORTING CITY DESC
    @Override
    public List<AddressEntity> addressServiceAllSortedByCityDesc() {
        return iAddressRepository.findAll(Sort.by(Sort.Direction.DESC, "addressDetails.city"));
    }

} //end AddressServiceImpl
