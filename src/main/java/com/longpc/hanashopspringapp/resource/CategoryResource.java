package com.longpc.hanashopspringapp.resource;

import com.longpc.hanashopspringapp.entities.CategoryEntity;
import com.longpc.hanashopspringapp.services.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryResource extends BaseResource<CategoryResource, CategoryEntity> {
    @Autowired
    ICategoryService categoryService;

    @GetMapping
    public ResponseEntity search() {
        try {
            return responseListDataObject(HttpStatus.INTERNAL_SERVER_ERROR, Message.SEARCH_SUCCESS.name(), categoryService.search());
        } catch (Exception e) {
            e.printStackTrace();
            return responseListDataObject(HttpStatus.INTERNAL_SERVER_ERROR, Message.SEARCH_FAIL.name(), null);
        }
    }

    @PostMapping
    public ResponseEntity insert(@RequestBody CategoryEntity categoryEntity) {
        try {
            categoryService.save(categoryEntity);
            List<CategoryEntity> categoryEntities = new ArrayList<>();
            categoryEntities.add(categoryEntity);
            return responseListDataObject(HttpStatus.OK, Message.CREATE_SUCCESS.name(), categoryEntities);
        } catch (Exception e) {
            e.printStackTrace();
            return responseListDataObject(HttpStatus.INTERNAL_SERVER_ERROR, Message.CREATE_FAIL.name(), null);
        }
    }

    @PutMapping
    public ResponseEntity update(@RequestBody CategoryEntity categoryEntity) {
        try {
            categoryService.save(categoryEntity);
            List<CategoryEntity> categoryEntities = new ArrayList<>();
            categoryEntities.add(categoryEntity);
            return responseListDataObject(HttpStatus.OK, Message.UPDATE_SUCCESS.name(), categoryEntities);
        } catch (Exception e) {
            e.printStackTrace();
            return responseListDataObject(HttpStatus.INTERNAL_SERVER_ERROR, Message.UPDATE_FAIL.name(), null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity update(@PathVariable String id) {
        try {
            categoryService.remove(id);
            List<String> categoryIds = new ArrayList<>();
            categoryIds.add(id);
            return responseListDataString(HttpStatus.OK, Message.REMOVE_SUCCESS.name(), categoryIds);
        } catch (Exception e) {
            e.printStackTrace();
            return responseListDataString(HttpStatus.INTERNAL_SERVER_ERROR, Message.REMOVE_FAIL.name(), null);
        }
    }
}
