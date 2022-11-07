package com.xuantoan.springboot.controller;

import com.xuantoan.springboot.controller.output.CustomReponse;
import com.xuantoan.springboot.entity.CategoryEntity;
import com.xuantoan.springboot.service.IBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/category")
public class CategoryRestController {
    @Autowired
    private IBaseService<CategoryEntity> categoryService;

    @GetMapping("")
    public Page<CategoryEntity> getAll(@RequestParam(name ="page",required = false, defaultValue = "1") Integer page,
                                               @RequestParam(name ="limit",required = false, defaultValue = "5") Integer limit,
                                               @RequestParam(name ="sortname",required = false, defaultValue = "DESC") String sortname,
                                               @RequestParam(name ="sortby",required = false, defaultValue = "id") String sortby){
        Sort sort = Sort.by(Sort.Direction.fromString(sortname),sortby);
        Pageable pageable = PageRequest.of(page-1,limit,sort);
        return categoryService.getAllPagingAndSorting(pageable);
    }

    @GetMapping("/{id}")
    public Optional<CategoryEntity> getOneByID(@PathVariable Long id) {
        return categoryService.getOneById(id);
    }

    @PostMapping("")
    public CategoryEntity save(@RequestBody CategoryEntity category) {
        return categoryService.saveOrUpdte(category);
    }

    @DeleteMapping("/{id}")
    public CustomReponse delete(@PathVariable Long id) {
        CustomReponse reponse = new CustomReponse();
        if(categoryService.delete(id)) {
            reponse.setStatus(HttpStatus.OK);
            reponse.setMessage("Xóa danh mục thành công!");
        }
        return  reponse;
    }
}
