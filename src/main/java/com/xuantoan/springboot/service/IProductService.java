package com.xuantoan.springboot.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IProductService<T> {
    Page<T> getByCategoryPagingAndSorting(Pageable pageable, String keyword,String category);
}
