package com.xuantoan.springboot.service.impl;

import com.xuantoan.springboot.entity.CategoryEntity;
import com.xuantoan.springboot.repository.CategoryRepository;
import com.xuantoan.springboot.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryService implements ICategoryService{

    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public List<CategoryEntity> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public CategoryEntity getCategoryById(Long id) {
        CategoryEntity pd = categoryRepository.findById(id).orElse(null);

        return pd;
    }

    @Override
    public CategoryEntity getCategoryByName(String name) {
        List<CategoryEntity> list = categoryRepository.findAll();
        for(CategoryEntity i : list){
            String cate1 = i.getCategoryName().toLowerCase();
            String cate2 = name.toLowerCase();
            if(cate1.equals(cate2))
                return i;
        }
        return null;
    }

    @Override
    public void delete(Long[] id) {
        for (Long i:id){
            categoryRepository.deleteById(i);
        }
    }

    @Override
    public CategoryEntity save(CategoryEntity category) {
        return categoryRepository.save(category);
    }
}
