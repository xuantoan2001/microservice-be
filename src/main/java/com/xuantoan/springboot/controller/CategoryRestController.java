package com.xuantoan.springboot.controller;

import com.xuantoan.springboot.entity.CategoryEntity;
import com.xuantoan.springboot.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryRestController {
    @Autowired
    private ICategoryService categoryService;

    @GetMapping( "/category")
    public List<CategoryEntity> listNew() {
        return categoryService.findAll();
    }

    @GetMapping( "/category/{id}")
    public CategoryEntity getCategoryById(@PathVariable Long id) {
        return categoryService.getCategoryById(id);
    }

    @PostMapping("/category")
    public CategoryEntity save(@RequestBody CategoryEntity category) {
        return categoryService.save(category);
    }

    @PutMapping("/category/{id}")
    public CategoryEntity update(@RequestBody CategoryEntity category, @PathVariable Long  id) {
        category.setId(id);
        return categoryService.save(category);
    }


}
