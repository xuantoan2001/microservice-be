package com.xuantoan.springboot.service.impl;

import com.xuantoan.springboot.entity.ProductEntity;
import com.xuantoan.springboot.exception.NotFoundException;
import com.xuantoan.springboot.repository.ProductRepository;
import com.xuantoan.springboot.service.IBaseService;
import com.xuantoan.springboot.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService  implements IBaseService<ProductEntity>, IProductService<ProductEntity> {
    @Autowired
    private  ProductRepository productRepository;

    @Override
    public Page<ProductEntity> getAllPagingAndSorting(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Page<ProductEntity> getAllPagingAndSorting(Pageable pageable, String keyword) {
        if(keyword==null || keyword.equals("null")){
            return productRepository.findAll(pageable);
        }
        if(keyword.length()>0)
            return productRepository.getAllPagingSortingAndSeaching(pageable,keyword.toLowerCase());
        else
            return productRepository.findAll(pageable);

    }

    @Override
    public Optional<ProductEntity> getOneById(Long id) {
        Optional<ProductEntity> productEntity = productRepository.findById(id);
        if(productEntity.isEmpty()){
            throw new NotFoundException("Không tìm thấy sản phẩm!");
        }
        return productEntity;
    }

    @Override
    public ProductEntity saveOrUpdate(ProductEntity entity) {
        ProductEntity old = new ProductEntity();
        if(entity.getId() != null){
            old = productRepository.findById(entity.getId()).orElse(null);
        }
        if(entity.getProductImage().length() == 0){
            assert old != null;
            entity.setProductImage(old.getProductImage());
        }
        return productRepository.save(entity);
    }

    @Override
    public boolean delete(Long id) {
       if(!productRepository.existsById(id)){
           throw new NotFoundException("ID KHÔNG TỒN TẠI");
       }
       productRepository.deleteById(id);
       return true;
    }

    @Override
    public  Optional<ProductEntity> getOneByName(String name) {
        return productRepository.findByProductName(name);
    }

    @Override
    public Page<ProductEntity> getByCategoryPagingAndSorting(Pageable pageable, String keyword, String category) {
        if(category == "all"|| category.equals("all"))
            return getAllPagingAndSorting( pageable, keyword);
        else{
            if(keyword==null || keyword.equals("null")){
                return productRepository.findAllByCategory_CategoryName(pageable,category);
            }
            if(keyword.length()>0)
                return productRepository.getByCategoryPagingSortingAndSeaching(pageable,keyword,category);
            else
                return productRepository.findAllByCategory_CategoryName(pageable,category);
        }
    }
}
