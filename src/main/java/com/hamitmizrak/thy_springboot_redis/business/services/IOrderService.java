package com.hamitmizrak.thy_springboot_redis.business.services;

import org.springframework.data.domain.Page;

import java.util.List;

// D: Dto
// E: Entity
public interface IOrderService<D, E>  {

    // ModelMapper
    public D entityToDto(E e);

    public E dtoToEntity(D d);

    ///////////////////////////////////////////////////////////////////
    // CRUD
    // CREATE
    public D orderServiceCreate(D d);

    // LIST
    public List<D> orderServiceList();

    // FIND BY ID
    public D orderServiceFindById(Long id);

    // UPDATE
    public D orderServiceUpdateById(Long id, D d);

    // DELETE
    public D orderServiceDeleteById(Long id);

    /////////////////////////////////////////////////////////////////////

    // PAGINATION
    public Page<E> orderServicePagination(int currentPage, int pageSize);

    // SORTING
    // Order Entityden belirli sutununa göre Sıramalama
    public List<E> orderServiceAllSortedBy(String sortedBy);

    // SORTING ASC
    // Default Olarak Order Entityden price göre Küçükten Büyüğe Doğru Sıralama
    public List<E> orderServiceAllSortedByCityAsc();

    // SORTING DESC
    // Default Olarak Order Entityden price göre Büyükten Küçüğe Doğru Sıralama
    public List<E> orderServiceAllSortedByCityDesc();

} //end IOService
