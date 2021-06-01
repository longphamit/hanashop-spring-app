package com.longpc.hanashopspringapp.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@Table(name = "user")
public class UserEntity {
    @Id
    private String id;
    private String username;
    private String password;
    private String avatar;
    private String gender;
    private String email;
    private  String status;
    private String fullname;
    private String phone;
    private String roleId;
    private Date loginAt;
    private Date createAt;
    private Date updateAt;
    private int point;


}
