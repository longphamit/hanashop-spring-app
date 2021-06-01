package com.longpc.hanashopspringapp.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

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
    private String imageId;
    private String status;
    private Date createAt;
}
