package com.xuantoan.springboot.service.impl;

import com.xuantoan.springboot.entity.CategoryEntity;
import com.xuantoan.springboot.exception.NotFoundException;
import com.xuantoan.springboot.exception.SQLException;
import com.xuantoan.springboot.repository.CategoryRepository;
import com.xuantoan.springboot.repository.ProductRepository;
import com.xuantoan.springboot.service.IBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Optional;

@Service
public class CategoryService implements IBaseService<CategoryEntity> {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;
    @Override
    public Page<CategoryEntity> getAllPagingAndSorting(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    @Override
    public Page<CategoryEntity> getAllPagingAndSorting(Pageable pageable, String keyword) {
        return null;
    }

    @Override
    public Optional<CategoryEntity> getOneById(Long id) {
        Optional<CategoryEntity> CategoryEntity = categoryRepository.findById(id);
        if(CategoryEntity.isEmpty()){
            throw new NotFoundException("Không tìm thấy sản phẩm!");
        }
        return CategoryEntity;
    }
    @Override
    public Optional<CategoryEntity> getOneByName(String name) {
        Optional<CategoryEntity> CategoryEntity = categoryRepository.findByCategoryName(name);
        if(CategoryEntity.isEmpty()){
            throw new NotFoundException("Không tìm danh mục!");
        }
        return CategoryEntity;
    }
    @Override
    public CategoryEntity saveOrUpdte(CategoryEntity entity) {
        CategoryEntity old = new CategoryEntity();
        if(entity.getId() != null){
            old = categoryRepository.findById(entity.getId()).orElse(null);
        }
        return categoryRepository.save(entity);
    }

    @Override
    public boolean delete(Long id) {
        if(!categoryRepository.existsById(id)){
            throw new NotFoundException("ID KHÔNG TỒN TẠI");
        }

        if(productRepository.findAllByCategory_Id(id).size() > 0){
            throw new SQLException("Vui lòng xóa hết những sản phẩm có trong danh mục trước!");
        }
        categoryRepository.deleteById(id);
        return true;
    }
}
