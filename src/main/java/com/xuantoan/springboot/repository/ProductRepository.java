package com.xuantoan.springboot.repository;

import com.xuantoan.springboot.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<ProductEntity, Long> {
    Page<ProductEntity> findAllByProductNameContainingOrDescriptionContaining(String name,String depcrip, Pageable pageable);
    List<ProductEntity> findAllByCategory_Id(Long id);

    @Query(nativeQuery = true,
            value = "SELECT * FROM product p " +
                    " JOIN p.category c"+
                    "ON "+
                    " WHERE p.product_name LIKE %Điện thoại iPhone 14%")
    Page<ProductEntity> getAllPagingSortingAndSeaching(Pageable pageable,String seach);

    //@Query//object (tenclass or ten table)
    //@Query(nativeQuery = true)//sql (tenclass or ten table)
    //spring vadidate form
}
