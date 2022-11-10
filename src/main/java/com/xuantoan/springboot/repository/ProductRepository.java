package com.xuantoan.springboot.repository;

import com.xuantoan.springboot.entity.CategoryEntity;
import com.xuantoan.springboot.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<ProductEntity, Long> {

    @Query(value = "select * from product as p inner join category ca on p.category_id= ca.id " +
            "where  p.product_name LIKE %:keyword% or p.description LIKE %:keyword%", nativeQuery = true,countQuery = "SELECT count(*) from product")
    Page<ProductEntity> getAllPagingSortingAndSeaching(Pageable pageable,@Param("keyword") String seach);

    @Query(value = "select * from product as p inner join category ca on p.category_id= ca.id " +
            "where  ca.category_name = :cate_name and (p.product_name LIKE %:keyword% or p.description LIKE %:keyword%)", nativeQuery = true,countQuery = "SELECT count(*) from product")
    Page<ProductEntity> getByCategoryPagingSortingAndSeaching(Pageable pageable,@Param("keyword") String seach,@Param("cate_name") String category);



    Page<ProductEntity> findAllByProductNameContainingOrDescriptionContaining(String name,String depcrip, Pageable pageable);
    List<ProductEntity> findAllByCategory_Id(Long id);
    Page<ProductEntity> findAllByCategory_CategoryName(Pageable pageable,String id);
    Optional<ProductEntity> findByProductName(String name);


    //@Query//object (tenclass or ten table)
    //@Query(nativeQuery = true)//sql (tenclass or ten table)
    //spring vadidate form

}
