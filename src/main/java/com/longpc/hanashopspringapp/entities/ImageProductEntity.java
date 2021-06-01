package com.longpc.hanashopspringapp.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name="image")
public class ImageProductEntity {
    @Id
    private String id;
    private String path;
    private String productId;
}
