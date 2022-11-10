package com.xuantoan.springboot.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IBaseService<T> {
    Page<T> getAllPagingAndSorting(Pageable pageable);
    Page<T> getAllPagingAndSorting(Pageable pageable,String keyword);
    Optional<T> getOneById(Long id);
    T saveOrUpdate(T entity);
    boolean delete(Long id);
    Optional<T> getOneByName(String name);
}
