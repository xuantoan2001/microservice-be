package com.xuantoan.springboot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name ="comment")
public class CommentEntity extends BaseEntity{
    @Column(name = "content")
    private String content;
    @Column(name = "created_date")
    private Date createdDate;

    @ManyToOne
    private ProductEntity product;


    @ManyToOne
    private UserEntity user;

}
