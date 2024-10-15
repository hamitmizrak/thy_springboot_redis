package com.hamitmizrak.thy_springboot_redis.business.services.impl;

import com.hamitmizrak.thy_springboot_redis.bean.ModelMapperBean;
import com.hamitmizrak.thy_springboot_redis.business.dto.AddressDto;
import com.hamitmizrak.thy_springboot_redis.business.services.IAddressService;
import com.hamitmizrak.thy_springboot_redis.data.entity.AddressEntity;
import com.hamitmizrak.thy_springboot_redis.data.repository.IAddressRepository;
import com.hamitmizrak.thy_springboot_redis.mapper.AddressMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        return null;
    }

    // LIST (Address)
    @Override
    public List<AddressDto> addressServiceList() {
        return List.of();
    }

    // FIND  (Address)
    @Override
    public AddressDto addressServiceFindById(Long id) {
        return null;
    }

    // UPDATE  (Address)
    @Transactional // Create, Update, Delete
    @Override
    public AddressDto addressServiceUpdateById(Long id, AddressDto addressDto) {
        return null;
    }

    // DELETE  (Address)
    @Transactional // Create, Update, Delete
    @Override
    public AddressDto addressServiceDeleteById(Long id) {
        return null;
    }
} //end AddressServiceImpl
