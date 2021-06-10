package com.longpc.hanashopspringapp.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="image")
public class ImageProductEntity {
    @Id
    private String id;
    private String path;
    @Column(name = "product_id")
    private String productId;
    @ManyToOne()
    @JoinColumn(name = "product_id",insertable = false,updatable = false)
    private ProductEntity product;
}
