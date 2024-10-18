package com.hamitmizrak.thy_springboot_redis.business.services.impl;

import com.hamitmizrak.thy_springboot_redis.bean.ModelMapperBean;
import com.hamitmizrak.thy_springboot_redis.business.dto.CustomerDto;
import com.hamitmizrak.thy_springboot_redis.business.services.ICustomerService;
import com.hamitmizrak.thy_springboot_redis.data.entity.CustomerEntity;
import com.hamitmizrak.thy_springboot_redis.data.repository.ICustomerRepository;
import com.hamitmizrak.thy_springboot_redis.exception._404_NotFoundException;
import com.hamitmizrak.thy_springboot_redis.mapper.CustomerMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

// LOMBOK
@RequiredArgsConstructor
@Log4j2

// Asıl İş Yükünü yapan yer
@Service
public class CustomerServiceImpl implements ICustomerService<CustomerDto, CustomerEntity> {

    // INJECTION
    private final ICustomerRepository iCustomerRepository;
    private final ModelMapperBean modelMapperBean;

    //////////////////////////////////////////////////////////////////////////////////
    // MODEL MAPPER
    @Override
    public CustomerDto entityToDto(CustomerEntity customerEntity) {
        // 1.YOL
        // return modelMapperBean.getModelMapperBeanMethod().map(customerEntity, CustomerDto.class);

        // 2.YOL
        return CustomerMapper.CustomerEntityToDto(customerEntity);
    }

    @Override
    public CustomerEntity dtoToEntity(CustomerDto customerDto) {
        // 1.YOL
        // return modelMapperBean.getModelMapperBeanMethod().map(customerDto, CustomerEntity.class);

        // 2.YOL
        return CustomerMapper.CustomerDtoToEntity(customerDto);
    }

    ////////////////////////////////////////////////////////////////////////////////
    // CREATE (Customer)
    @Transactional // Create, Update, Delete
    @Override
    public CustomerDto customerServiceCreate(CustomerDto customerDto) {
        CustomerEntity customerCreateEntity = dtoToEntity(customerDto);
        customerCreateEntity = iCustomerRepository.save(customerCreateEntity);
        return entityToDto(customerCreateEntity);
    }

    // LIST (Customer)
    @Override
    public List<CustomerDto> customerServiceList() {
        return iCustomerRepository.findAll().stream().map(CustomerMapper::CustomerEntityToDto).collect(Collectors.toList());
    }

    // FIND  (Customer)
    @Override
    public CustomerDto customerServiceFindById(Long id) {
        return iCustomerRepository.findById(id)
                .map(CustomerMapper::CustomerEntityToDto)
                .orElseThrow(() -> new _404_NotFoundException(id + " nolu Customer bulunamadı"));
    }

    // UPDATE  (Customer)
    @Transactional // Create, Update, Delete
    @Override
    public CustomerDto customerServiceUpdateById(Long id, CustomerDto customerDto) {
        // Önce Nesne var mı yokmu
        CustomerEntity customerUpdateEntity = dtoToEntity(customerServiceFindById(id));
        customerUpdateEntity.setName(customerDto.getName());
        customerUpdateEntity.setSurname(customerDto.getSurname());
        customerUpdateEntity.setCreatedDate(customerDto.getCreatedDate());
        customerUpdateEntity = iCustomerRepository.save(customerUpdateEntity);
        return entityToDto(customerUpdateEntity);
    }

    // DELETE  (Customer)
    @Transactional // Create, Update, Delete
    @Override
    public CustomerDto customerServiceDeleteById(Long id) {
        // Önce Nesne var mı yokmu
        CustomerEntity customerDeleteEntity = dtoToEntity(customerServiceFindById(id));
        iCustomerRepository.delete(customerDeleteEntity);
        return entityToDto(customerDeleteEntity);
    }

    //////////////////////////////////////////////////////////////////////////////////////
    // PAGINATION
    // import org.springframework.data.domain.Page;
    // import org.springframework.data.domain.Pageable;
    @Override
    public Page<CustomerEntity> customerServicePagination(int currentPage, int pageSize) {
        Pageable pageable = PageRequest.of(currentPage, pageSize);
        Page<CustomerEntity> customerEntityPage = iCustomerRepository.findAll(pageable);
        return customerEntityPage;
    }

    // SORTING BELLI SUTUNA GORE
    @Override
    public List<CustomerEntity> customerServiceAllSortedBy(String sortedBy) {
        // NOT: JpaRepository'de findAll Sorting kullanabilirim.
        return iCustomerRepository.findAll(Sort.by(Sort.Direction.ASC, sortedBy));
        //return iAddressRepository.findAll(Sort.by(Sort.Direction.DESC, sortedBy));
    }

    // SORTING CITY ASC
    @Override
    public List<CustomerEntity> customerServiceAllSortedByCityAsc() {
        return iCustomerRepository.findAll(Sort.by(Sort.Direction.ASC, "surname"));
    }

    // SORTING CITY DESC
    @Override
    public List<CustomerEntity> customerServiceAllSortedByCityDesc() {
        return iCustomerRepository.findAll(Sort.by(Sort.Direction.DESC, "surname"));
    }

} //end CustomerServiceImpl
