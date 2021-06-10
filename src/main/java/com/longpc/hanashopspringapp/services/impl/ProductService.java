package com.longpc.hanashopspringapp.services.impl;

import com.longpc.hanashopspringapp.entities.ProductEntity;
import com.longpc.hanashopspringapp.repositories.ImageRepository;
import com.longpc.hanashopspringapp.repositories.ProductRepository;
import com.longpc.hanashopspringapp.services.IImageService;
import com.longpc.hanashopspringapp.services.IProductService;
import com.longpc.hanashopspringapp.utils.IDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
@Service
public class ProductService implements IProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private IImageService imageService;

    @Override
    public List<ProductEntity> search() throws Exception {
        return productRepository.findAll();
    }
    @Override
    public void save(ProductEntity productEntity, List<MultipartFile>images) throws Exception {
        productEntity.setId(IDUtil.generateID());
        productEntity.setCreateAt(new Timestamp(new Date().getTime()));
        productRepository.save(productEntity);
        imageService.saveProductImage(productEntity,images);
    }

    @Override
    public void update(ProductEntity productEntity, MultipartFile image) throws Exception {

    }
}
