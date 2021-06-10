package com.longpc.hanashopspringapp.services.impl;

import com.longpc.hanashopspringapp.entities.BrandEntity;
import com.longpc.hanashopspringapp.repositories.BrandRepository;
import com.longpc.hanashopspringapp.services.IBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandService implements IBrandService {
    @Autowired
    BrandRepository repository;
    @Override
    public List<BrandEntity> search() {
        return repository.findAll();
    }
}
