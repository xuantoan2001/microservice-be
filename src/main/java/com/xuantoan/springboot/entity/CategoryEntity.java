package com.xuantoan.springboot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Entity
@Table(name = "category")
public class CategoryEntity extends BaseEntity{

    @Column(name = "category_name")
    private String categoryName;


    @OneToMany(mappedBy = "category",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonIgnore
    private List<ProductEntity> products = new ArrayList<>();


    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public CategoryEntity(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<ProductEntity> getProducts() {
        return products;
    }

    public void setProducts(List<ProductEntity> products) {
        this.products = products;
    }

    public CategoryEntity(String categoryName, List<ProductEntity> products) {
        this.categoryName = categoryName;
        this.products = products;
    }

    public CategoryEntity() {
    }
}
