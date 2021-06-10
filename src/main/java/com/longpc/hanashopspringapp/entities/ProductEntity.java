package com.longpc.hanashopspringapp.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name="product")
public class ProductEntity {
    @Id
    private String id;
    private String name;
    private String description;
    private int quantity;
    private double price;
    private String categoryId;
    private String brandId;
    private String status;
    private Date createAt;
    @OneToMany(mappedBy = "product",fetch = FetchType.LAZY)
    private List<ImageProductEntity> imageList;
}
