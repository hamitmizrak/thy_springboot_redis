package com.hamitmizrak.thy_springboot_redis.business.services;

import org.springframework.data.domain.Page;

import java.util.List;

// D: Dto
// E: Entity
public interface IProductService<D, E>  {

    // ModelMapper
    public D entityToDto(E e);

    public E dtoToEntity(D d);

    ///////////////////////////////////////////////////////////////////
    // CRUD
    // CREATE
    public D productServiceCreate(D d);

    // LIST
    public List<D> productServiceList();

    // FIND BY ID
    public D productServiceFindById(Long id);

    // UPDATE
    public D productServiceUpdateById(Long id, D d);

    // DELETE
    public D productServiceDeleteById(Long id);

    /////////////////////////////////////////////////////////////////////

    // PAGINATION
    public Page<E> productServicePagination(int currentPage, int pageSize);

    // SORTING
    // product Entityden belirli sutununa göre Sıramalama
    public List<E> productServiceAllSortedBy(String sortedBy);

    // SORTING ASC
    // Default Olarak product Entityden price göre Küçükten Büyüğe Doğru Sıralama
    public List<E> productServiceAllSortedByCityAsc();

    // SORTING DESC
    // Default Olarak product Entityden price göre Büyükten Küçüğe Doğru Sıralama
    public List<E> productServiceAllSortedByCityDesc();

} //end IOService
