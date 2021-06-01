package com.longpc.hanashopspringapp.services;

import com.longpc.hanashopspringapp.entities.ProductEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IProductService {
    List<ProductEntity> search() throws Exception;

    void save(ProductEntity productEntity, MultipartFile image) throws Exception;

    void update(ProductEntity productEntity, MultipartFile image) throws Exception;

}
