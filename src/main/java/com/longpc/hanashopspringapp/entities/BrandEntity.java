package com.longpc.hanashopspringapp.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "brand")
public class BrandEntity {
    @Id
    private String id;
    private String name;
}
