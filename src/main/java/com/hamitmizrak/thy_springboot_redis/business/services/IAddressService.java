package com.hamitmizrak.thy_springboot_redis.business.services;

import org.springframework.data.domain.Page;

import java.util.List;

// D: Dto
// E: Entity
public interface IAddressService<D, E>  {

    // ModelMapper
    public D entityToDto(E e);

    public E dtoToEntity(D d);

    ///////////////////////////////////////////////////////////////////
    // CRUD
    // CREATE
    public D addressServiceCreate(D d);

    // LIST
    public List<D> addressServiceList();

    // FIND BY ID
    public D addressServiceFindById(Long id);

    // UPDATE
    public D addressServiceUpdateById(Long id, D d);

    // DELETE
    public D addressServiceDeleteById(Long id);

    /////////////////////////////////////////////////////////////////////

    // PAGINATION
    public Page<E> addressAllServicePagination(int currentPage, int pageSize);

    // Adresi belirli bir sütuna göre sıralama
    public List<E> addressAllServiceSorted(String sortBy);

    // Varsayılan olarak adresi şehire göre sıralama
    public List<E> addressAllServiceSortedByCity();

} //end IAddressService
