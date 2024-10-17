package com.hamitmizrak.thy_springboot_redis.business.services.impl;

import com.hamitmizrak.thy_springboot_redis.bean.ModelMapperBean;
import com.hamitmizrak.thy_springboot_redis.business.dto.OrderDto;
import com.hamitmizrak.thy_springboot_redis.business.services.IOrderService;
import com.hamitmizrak.thy_springboot_redis.data.entity.OrderEntity;
import com.hamitmizrak.thy_springboot_redis.data.repository.IOrderRepository;
import com.hamitmizrak.thy_springboot_redis.exception._404_NotFoundException;
import com.hamitmizrak.thy_springboot_redis.mapper.OrderMapper;
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
public class OrderServiceImpl implements IOrderService<OrderDto, OrderEntity> {

    // INJECTION
    private final IOrderRepository iOrderRepository;
    private final ModelMapperBean modelMapperBean;


    //////////////////////////////////////////////////////////////////////////////////
    // MODEL MAPPER
    @Override
    public OrderDto entityToDto(OrderEntity orderEntity) {
        // 1.YOL
        // return modelMapperBean.getModelMapperBeanMethod().map(orderEntity, OrderDto.class);

        // 2.YOL
        return OrderMapper.OrderEntityToDto(orderEntity);
    }

    @Override
    public OrderEntity dtoToEntity(OrderDto orderDto) {
        // 1.YOL
        // return modelMapperBean.getModelMapperBeanMethod().map(orderDto, OrderEntity.class);

        // 2.YOL
        return OrderMapper.OrderDtoToEntity(orderDto);
    }

    ////////////////////////////////////////////////////////////////////////////////
    // CREATE (Customer)
    @Transactional // Create, Update, Delete
    @Override
    public OrderDto orderServiceCreate(OrderDto orderDto) {
        OrderEntity orderEntity = dtoToEntity(orderDto);
        orderEntity = iOrderRepository.save(orderEntity);
        return entityToDto(orderEntity);
    }

    // LIST (Customer)
    @Override
    public List<OrderDto> orderServiceList() {
        return iOrderRepository.findAll().stream().map(OrderMapper::OrderEntityToDto).collect(Collectors.toList());
    }

    // FIND  (Customer)
    @Override
    public OrderDto orderServiceFindById(Long id) {
        return iOrderRepository.findById(id)
                .map(OrderMapper::OrderEntityToDto)
                .orElseThrow(() -> new _404_NotFoundException(id + " nolu Order bulunamadı"));
    }

    // UPDATE  (Customer)
    @Transactional // Create, Update, Delete
    @Override
    public OrderDto orderServiceUpdateById(Long id, OrderDto orderDto) {
        // Önce Nesne var mı yokmu
        OrderEntity orderEntity = dtoToEntity(orderServiceFindById(id));
        orderEntity.setName(orderDto.getName());
        orderEntity.setPrice(orderDto.getPrice());
        orderEntity.setCode(orderDto.getCode());
        orderEntity.setCreatedDate(orderDto.getCreatedDate());
        orderEntity = iOrderRepository.save(orderEntity);
        return entityToDto(orderEntity);
    }

    // DELETE  (Customer)
    @Transactional // Create, Update, Delete
    @Override
    public OrderDto orderServiceDeleteById(Long id) {
        // Önce Nesne var mı yokmu
        OrderEntity customerDeleteEntity = dtoToEntity(orderServiceFindById(id));
        iOrderRepository.delete(customerDeleteEntity);
        return entityToDto(customerDeleteEntity);
    }

    //////////////////////////////////////////////////////////////////////////////////////
    // PAGINATION
    // import org.springframework.data.domain.Page;
    // import org.springframework.data.domain.Pageable;
    @Override
    public Page<OrderEntity> orderServicePagination(int currentPage, int pageSize) {
        Pageable pageable = PageRequest.of(currentPage, pageSize);
        Page<OrderEntity> orderEntityPage = iOrderRepository.findAll(pageable);
        return orderEntityPage;
    }

    // SORTING BELLI SUTUNA GORE
    @Override
    public List<OrderEntity> orderServiceAllSortedBy(String sortedBy) {
        // NOT: JpaRepository'de findAll Sorting kullanabilirim.
        return iOrderRepository.findAll(Sort.by(Sort.Direction.ASC, sortedBy));
        //return iAddressRepository.findAll(Sort.by(Sort.Direction.DESC, sortedBy));
    }

    // SORTING CITY ASC
    @Override
    public List<OrderEntity> orderServiceAllSortedByCityAsc() {
        return iOrderRepository.findAll(Sort.by(Sort.Direction.ASC, "price"));
    }

    // SORTING CITY DESC
    @Override
    public List<OrderEntity> orderServiceAllSortedByCityDesc() {
        return iOrderRepository.findAll(Sort.by(Sort.Direction.DESC, "price"));
    }

} //end CustomerServiceImpl
