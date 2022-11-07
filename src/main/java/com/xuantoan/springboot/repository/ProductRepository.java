package com.xuantoan.springboot.repository;

import com.xuantoan.springboot.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<ProductEntity, Long> {
    Page<ProductEntity> findAllByProductNameContainingOrDescriptionContaining(String name,String depcrip, Pageable pageable);
    //@Query//object (tenclass or ten table)
    //@Query(nativeQuery = true)//sql (tenclass or ten table)
    //spring vadidate form
}
