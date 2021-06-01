package com.longpc.hanashopspringapp.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "productlog")
public class ProductLogEntity {
    @Id
    private String id;
    private String name;
    private String productId;
}
