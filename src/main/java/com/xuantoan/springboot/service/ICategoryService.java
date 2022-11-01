package com.xuantoan.springboot.service;

import com.xuantoan.springboot.entity.CategoryEntity;

import java.util.List;

public interface ICategoryService {

    List<CategoryEntity> findAll();

    CategoryEntity save(CategoryEntity category);

    CategoryEntity getCategoryById(Long id);

    CategoryEntity getCategoryByName(String name);

    void delete(Long[] id);
}
