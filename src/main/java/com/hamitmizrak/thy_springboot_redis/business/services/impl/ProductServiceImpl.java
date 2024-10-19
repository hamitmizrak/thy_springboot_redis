package com.hamitmizrak.thy_springboot_redis.business.services.impl;

import com.hamitmizrak.thy_springboot_redis.bean.ModelMapperBean;
import com.hamitmizrak.thy_springboot_redis.business.dto.ProductDto;
import com.hamitmizrak.thy_springboot_redis.business.services.IProductService;
import com.hamitmizrak.thy_springboot_redis.data.entity.ProductEntity;
import com.hamitmizrak.thy_springboot_redis.data.repository.IProductRepository;
import com.hamitmizrak.thy_springboot_redis.exception._404_NotFoundException;
import com.hamitmizrak.thy_springboot_redis.mapper.ProductMapper;
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
public class ProductServiceImpl implements IProductService<ProductDto, ProductEntity> {

    // INJECTION
    private final IProductRepository iProductRepository;
    private final ModelMapperBean modelMapperBean;

    //////////////////////////////////////////////////////////////////////////////////
    // MODEL MAPPER
    @Override
    public ProductDto entityToDto(ProductEntity productEntity) {
        // 1.YOL
        // return modelMapperBean.getModelMapperBeanMethod().map(productEntity, ProductDto.class);

        // 2.YOL
        return ProductMapper.ProductEntityToDto(productEntity);
    }

    @Override
    public ProductEntity dtoToEntity(ProductDto productDto) {
        // 1.YOL
        // return modelMapperBean.getModelMapperBeanMethod().map(productDto, ProductEntity.class);

        // 2.YOL
        return ProductMapper.ProductDtoToEntity(productDto);
    }

    ////////////////////////////////////////////////////////////////////////////////
    // CREATE (Product)
    @Transactional // Create, Update, Delete
    @Override
    public ProductDto productServiceCreate(ProductDto productDto) {
        ProductEntity productEntity = dtoToEntity(productDto);
        productEntity = iProductRepository.save(productEntity);
        return entityToDto(productEntity);
    }

    // LIST (Product)
    @Override
    public List<ProductDto> productServiceList() {
        return iProductRepository
                .findAll()
                .stream()
                .map(ProductMapper::ProductEntityToDto)
                .collect(Collectors.toList());
    }

    // FIND  (Product)
    @Override
    public ProductDto productServiceFindById(Long id) {
        return iProductRepository.findById(id)
                .map(ProductMapper::ProductEntityToDto)
                .orElseThrow(() -> new _404_NotFoundException(id + " nolu Product bulunamadı"));
    }

    // UPDATE  (Product)
    @Transactional // Create, Update, Delete
    @Override
    public ProductDto productServiceUpdateById(Long id, ProductDto productDto) {
        // Önce Nesne var mı yokmu
        ProductEntity productEntity = dtoToEntity(productServiceFindById(id));
        productEntity.setName(productDto.getName());
        productEntity.setPrice(productDto.getPrice());
        productEntity.setCreatedDate(productDto.getCreatedDate());
        productEntity = iProductRepository.save(productEntity);
        return entityToDto(productEntity);
    }

    // DELETE  (Product)
    @Transactional // Create, Update, Delete
    @Override
    public ProductDto productServiceDeleteById(Long id) {
        // Önce Nesne var mı yokmu
        ProductEntity productDeleteEntity = dtoToEntity(productServiceFindById(id));
        iProductRepository.delete(productDeleteEntity);
        return entityToDto(productDeleteEntity);
    }

    //////////////////////////////////////////////////////////////////////////////////////
    // PAGINATION
    // import org.springframework.data.domain.Page;
    // import org.springframework.data.domain.Pageable;
    @Override
    public Page<ProductEntity> productServicePagination(int currentPage, int pageSize) {
        Pageable pageable = PageRequest.of(currentPage, pageSize);
        Page<ProductEntity> productEntityPage = iProductRepository.findAll(pageable);
        return productEntityPage;
    }

    // SORTING BELLI SUTUNA GORE
    @Override
    public List<ProductEntity> productServiceAllSortedBy(String sortedBy) {
        // NOT: JpaRepository'de findAll Sorting kullanabilirim.
        return iProductRepository.findAll(Sort.by(Sort.Direction.ASC, sortedBy));
        //return iAddressRepository.findAll(Sort.by(Sort.Direction.DESC, sortedBy));
    }

    // SORTING CITY ASC
    @Override
    public List<ProductEntity> productServiceAllSortedByCityAsc() {
        return iProductRepository.findAll(Sort.by(Sort.Direction.ASC, "price"));
    }

    // SORTING CITY DESC
    @Override
    public List<ProductEntity> productServiceAllSortedByCityDesc() {
        return iProductRepository.findAll(Sort.by(Sort.Direction.DESC, "price"));
    }

} //end ProductServiceImpl
