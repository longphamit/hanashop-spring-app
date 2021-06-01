package com.longpc.hanashopspringapp.services.impl;

import com.longpc.hanashopspringapp.entities.CategoryEntity;
import com.longpc.hanashopspringapp.repositories.CategoryRepository;
import com.longpc.hanashopspringapp.services.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryService implements ICategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<CategoryEntity> search() throws Exception {
        return categoryRepository.findAll();
    }

    public void save(CategoryEntity categoryEntity) throws Exception{
        if(null==categoryEntity.getId()||categoryEntity.getId().length()==0){
            categoryEntity.setId(UUID.randomUUID().toString());
            categoryRepository.save(categoryEntity);
        }else{
            categoryRepository.save(categoryEntity);
        }
    }
    public void remove(String id) throws Exception{
        categoryRepository.deleteById(id);
    }
}
