package com.longpc.hanashopspringapp.entities;

import lombok.Data;

import javax.persistence.*;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}
