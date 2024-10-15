package com.hamitmizrak.thy_springboot_redis.business.services.impl;

import com.hamitmizrak.thy_springboot_redis.bean.ModelMapperBean;
import com.hamitmizrak.thy_springboot_redis.business.dto.AddressDto;
import com.hamitmizrak.thy_springboot_redis.business.services.IAddressService;
import com.hamitmizrak.thy_springboot_redis.data.entity.AddressEntity;
import com.hamitmizrak.thy_springboot_redis.data.repository.IAddressRepository;
import com.hamitmizrak.thy_springboot_redis.exception._404_NotFoundException;
import com.hamitmizrak.thy_springboot_redis.mapper.AddressMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

// LOMBOK
@RequiredArgsConstructor
@Log4j2

// Asıl İş Yükünü yapan yer
@Service
public class AddressServiceImpl implements IAddressService<AddressDto, AddressEntity> {

    // INJECTION
    private final IAddressRepository iAddressRepository;
    private final ModelMapperBean modelMapperBean;

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
        AddressEntity addressUpdateEntity =dtoToEntity(addressServiceFindById(id));

        // Bulunan Nesneyi Set
        addressUpdateEntity.setDoorNumber(addressDto.getDoorNumber());
        addressUpdateEntity.setStreet(addressDto.getStreet());
        addressUpdateEntity.setAvenue(addressDto.getAvenue());
        addressUpdateEntity.setCity(addressDto.getCity());
        addressUpdateEntity.setZipCode(addressDto.getZipCode());
        addressUpdateEntity.setState(addressDto.getState());
        addressUpdateEntity.setDescription(addressDto.getDescription());
        addressUpdateEntity.setCreatedDate(addressDto.getCreatedDate());
        addressUpdateEntity=iAddressRepository.save(addressUpdateEntity);
        return entityToDto(addressUpdateEntity);
    }

    // DELETE  (Address)
    @Transactional // Create, Update, Delete
    @Override
    public AddressDto addressServiceDeleteById(Long id) {
        // Önce Nesne var mı yokmu
        AddressEntity addressDeleteEntity =dtoToEntity(addressServiceFindById(id));
        iAddressRepository.delete(addressDeleteEntity);
        return entityToDto(addressDeleteEntity);
    }

    ////////////////////////////////////////////////////////////////////
    // getAllHeaderData
    @Override
    public void headerService(Map<String, String> headers) {
        headers.forEach((key, value) -> {
            System.out.println("HeaderName: " + key + " HeaderValue: " + value);
        });
    }

    //App Information
    @Override
    public String appInformationService(HttpServletRequest request, HttpServletResponse response) {
        String URI = request.getRequestURI();
        String LOCALHOST = request.getLocalAddr();
        String SESSION = request.getSession().toString();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("uri: " + URI).append("<br/>localhost: " + LOCALHOST).append("<br/>session: " + SESSION);
        String informationToString = stringBuilder.toString();
        return informationToString;
    }

    ////////////////////////////////////////////////////////////////////
    // LIST PAGINATION Entity
    @Override
    public Page<AddressEntity> addressServicePagination(int currentPage, int pageSize) {
        Pageable pageable = PageRequest.of(currentPage, pageSize);
        Page<AddressEntity> blogRepositoryAll = iAddressRepository.findAll(pageable);
        return blogRepositoryAll;
    }

    @Override
    public Page<AddressDto> addressServicePageable(Pageable pageable, AddressDto addressDto) {
        List<AddressDto> dtoList = new ArrayList<>();

        return iAddressRepository.findAll(pageable).map(new Function<AddressEntity, AddressDto>() {
            @Override
            // Entity Dto Çevirmek  (Model Mapper kulalnabilirsin)
            public AddressDto apply (AddressEntity registerEntity){
                AddressDto registerDto = new AddressDto();
                registerDto.setCity(registerEntity.getCity());
                registerDto.setStreet(registerEntity.getStreet());
                registerDto.setDescription(registerEntity.getDescription());
                registerDto.setId(registerEntity.getId());
                registerDto.setAvenue(registerEntity.getAvenue());
                registerDto.setDoorNumber(registerEntity.getDoorNumber());
                registerDto.setCreatedDate(registerEntity.getCreatedDate());
                dtoList.add(registerDto);
                return registerDto;
            }
        });
    }

} //end AddressServiceImpl
