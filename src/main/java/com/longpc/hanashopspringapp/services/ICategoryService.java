package com.longpc.hanashopspringapp.services;

import com.longpc.hanashopspringapp.entities.CategoryEntity;

import java.util.List;

public interface ICategoryService {
    List<CategoryEntity> search() throws Exception;

    void save(CategoryEntity categoryEntity) throws Exception;

    void remove(String id) throws Exception;
}
