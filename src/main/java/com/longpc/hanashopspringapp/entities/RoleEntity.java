package com.longpc.hanashopspringapp.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "role")
public class RoleEntity {
    @Id
    private String id;
    private String name;
}
