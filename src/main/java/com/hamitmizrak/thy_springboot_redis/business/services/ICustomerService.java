package com.hamitmizrak.thy_springboot_redis.business.services;

import org.springframework.data.domain.Page;

import java.util.List;

// D: Dto
// E: Entity
public interface ICustomerService<D, E>  {

    // ModelMapper
    public D entityToDto(E e);

    public E dtoToEntity(D d);

    ///////////////////////////////////////////////////////////////////
    // CRUD
    // CREATE
    public D customerServiceCreate(D d);

    // LIST
    public List<D> customerServiceList();

    // FIND BY ID
    public D customerServiceFindById(Long id);

    // UPDATE
    public D customerServiceUpdateById(Long id, D d);

    // DELETE
    public D customerServiceDeleteById(Long id);

    /////////////////////////////////////////////////////////////////////

    // PAGINATION
    public Page<E> customerServicePagination(int currentPage, int pageSize);

    // SORTING
    // Adres Entityden belirli sutununa göre Sıramalama
    public List<E> customerServiceAllSortedBy(String sortedBy);

    // SORTING ASC
    // Default Olarak Addres Entityden Şehire göre Küçükten Büyüğe Doğru Sıralama
    public List<E> customerServiceAllSortedByCityAsc();

    // SORTING DESC
    // Default Olarak Addres Entityden Şehire göre Büyükten Küçüğe Doğru Sıralama
    public List<E> customerServiceAllSortedByCityDesc();

} //end ICustomerService
