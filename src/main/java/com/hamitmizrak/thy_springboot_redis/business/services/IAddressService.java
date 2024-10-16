package com.hamitmizrak.thy_springboot_redis.business.services;

import org.springframework.data.domain.Page;

import java.util.List;

// D: Dto
// E: Entity
public interface IAddressService<D, E> extends IApplicationInformationService {

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
    public Page<E> addressServicePagination(int currentPage, int pageSize);

} //end IAddressService
