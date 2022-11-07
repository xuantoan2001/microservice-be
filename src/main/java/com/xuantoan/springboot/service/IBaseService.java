package com.xuantoan.springboot.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IBaseService<T> {
    Page<T> getAllPagingAndSorting(Pageable pageable);
    Optional<T> getOneById(Long id);
    T saveOrUpdte(T entity);
    boolean delete(Long id);
}
