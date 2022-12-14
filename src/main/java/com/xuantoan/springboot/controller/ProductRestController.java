package com.xuantoan.springboot.controller;

import com.xuantoan.springboot.controller.output.CustomReponse;
import com.xuantoan.springboot.entity.ProductEntity;
import com.xuantoan.springboot.service.IBaseService;
import com.xuantoan.springboot.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/product")
public class ProductRestController {
    @Autowired
    private IBaseService<ProductEntity> productService;
    @Autowired
    private IProductService<ProductEntity> productService2;
    @GetMapping("")
    public Page<ProductEntity> getAll(@RequestParam(name ="page",required = false, defaultValue = "1") Integer page,
                                      @RequestParam(name ="limit",required = false, defaultValue = "5") Integer limit,
                                      @RequestParam(name ="sortname",required = false, defaultValue = "DESC") String sortname,
                                      @RequestParam(name ="sortby",required = false, defaultValue = "id") String sortby,
                                      @RequestParam(name ="seach",required = false) String keyword){
        Sort sort = Sort.by(Sort.Direction.fromString(sortname),sortby);
        Pageable pageable = PageRequest.of(page-1,limit,sort);
        return productService.getAllPagingAndSorting(pageable,keyword);
    }
    @GetMapping("/category")
    public Page<ProductEntity> getAll(
                                      @RequestParam(name ="page",required = false, defaultValue = "1") Integer page,
                                      @RequestParam(name ="limit",required = false, defaultValue = "5") Integer limit,
                                      @RequestParam(name ="sortname",required = false, defaultValue = "DESC") String sortname,
                                      @RequestParam(name ="sortby",required = false, defaultValue = "id") String sortby,
                                      @RequestParam(name ="category",required = false, defaultValue = "all") String category,
                                      @RequestParam(name ="seach",required = false) String keyword){
        Sort sort = Sort.by(Sort.Direction.fromString(sortname),sortby);
        Pageable pageable = PageRequest.of(page-1,limit,sort);
        return productService2.getByCategoryPagingAndSorting(pageable,keyword,category);
    }

    @GetMapping("/{id}")
    public Optional<ProductEntity> getOneByID(@PathVariable Long id) {
        return productService.getOneById(id);
    }

    @GetMapping("/name/{name}")
    public Optional<ProductEntity> getOneByName(@PathVariable String name) {
        return productService.getOneByName(name);
    }

    @PostMapping("")
    public ProductEntity save(@RequestBody ProductEntity product) {
        return productService.saveOrUpdate(product);
    }

    @DeleteMapping("/{id}")
    public CustomReponse delete(@PathVariable Long id) {
        CustomReponse reponse = new CustomReponse();
        if(productService.delete(id)) {
            reponse.setStatus(HttpStatus.OK);
            reponse.setMessage("X??a s???n ph???m th??nh c??ng!");
        }
        return  reponse;
    }
}
