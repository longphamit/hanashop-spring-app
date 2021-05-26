package com.longpc.hanashopspringapp.services;

import com.longpc.hanashopspringapp.entities.CategoryEntity;

import java.util.List;

public interface ICategoryService {
    public List<CategoryEntity> search() throws Exception;
    public void save(CategoryEntity categoryEntity) throws Exception;
    public void remove(String id) throws Exception;
}
