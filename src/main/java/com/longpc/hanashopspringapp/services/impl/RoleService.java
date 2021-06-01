package com.longpc.hanashopspringapp.services.impl;

import com.longpc.hanashopspringapp.entities.RoleEntity;
import com.longpc.hanashopspringapp.repositories.RoleRepository;
import com.longpc.hanashopspringapp.repositories.UserRepository;
import com.longpc.hanashopspringapp.services.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService implements IRoleService {
    @Autowired
    RoleRepository roleRepository;
    @Override
    public List<RoleEntity> search() {
       return roleRepository.findAll();
    }
}
