package com.xuantoan.springboot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "category")
public class CategoryEntity extends BaseEntity{

    @Column(name = "category_name")
    private String categoryName;

    @OneToMany(mappedBy = "category",
                orphanRemoval = true)
    @JsonIgnore
    private List<ProductEntity> products = new ArrayList<>();

}
