package com.xuantoan.springboot.service.impl;

import com.xuantoan.springboot.entity.ProductEntity;
import com.xuantoan.springboot.repository.ProductRepository;
import com.xuantoan.springboot.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService implements IProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductEntity> findAll() {
        List<ProductEntity> list =  productRepository.findAll();

        return list;
    }

    @Override
    public List<ProductEntity> getProductByCategoryId(Long id) {
        List<ProductEntity> list = productRepository.findAll();
        List<ProductEntity> data = new ArrayList<>();
        for(ProductEntity i : list){
            Long cate1_id = i.getCategory().getId();
            if(cate1_id == id)
                data.add(i);
        }
        return data;
    }

    @Override
    public ProductEntity save(ProductEntity product) {
        ProductEntity old = new ProductEntity();
        if(product.getId() != null){
            old = productRepository.findById(product.getId()).orElse(null);
        }
        //int oi = product.getProductImage().length();
        if(product.getProductImage().length() == 0){
            assert old != null;
            product.setProductImage(old.getProductImage());
        }
        return productRepository.save(product);
    }

    @Override
    public void delete(Long[] id) {
        for (Long i:id){
            productRepository.deleteById(i);
        }
    }

    @Override
    public ProductEntity getProductById(Long id) {
        ProductEntity pd = productRepository.findById(id).orElse(null);
        return pd;
    }


}
