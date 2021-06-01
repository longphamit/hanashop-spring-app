package com.longpc.hanashopspringapp.services.impl;

import com.longpc.hanashopspringapp.entities.ProductEntity;
import com.longpc.hanashopspringapp.services.IProductService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@Service
public class ProductService implements IProductService {
    @Override
    public List<ProductEntity> search() throws Exception {
        return null;
    }

    @Override
    public void save(ProductEntity productEntity, MultipartFile image) throws Exception {

    }

    @Override
    public void update(ProductEntity productEntity, MultipartFile image) throws Exception {

    }
}
