package com.xuantoan.springboot.controller;

import com.xuantoan.springboot.entity.ProductEntity;
import com.xuantoan.springboot.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductRestController {
    @Autowired
    private IProductService productService;

    @GetMapping( "/product")
    public List<ProductEntity> listNew() {
        return productService.findAll();
    }

    @GetMapping( "/product/{id}")
    public ProductEntity getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @GetMapping( "/product/category/{id}")
    public List<ProductEntity> getProductByCategoryId(@PathVariable Long id) {
        return productService.getProductByCategoryId(id);
    }

    @PostMapping("/product")
    public ProductEntity save(@RequestBody ProductEntity product) {
        return productService.save(product);
    }

    @PutMapping("/product/{id}")
    public ProductEntity update(@RequestBody ProductEntity product, @PathVariable Long  id) {
        product.setId(id);
        return productService.save(product);
    }

    @DeleteMapping("/product")
    public void delete(@RequestBody Long[]  ids) {
        productService.delete(ids);
    }
}
