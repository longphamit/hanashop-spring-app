package com.longpc.hanashopspringapp.services;

import com.longpc.hanashopspringapp.entities.ProductEntity;
import com.longpc.hanashopspringapp.entities.UserEntity;
import org.springframework.web.multipart.MultipartFile;

public interface IImageService {
    void updateUserImage(UserEntity userEntity, MultipartFile avatar) throws Exception;

    void saveUserImage(UserEntity userEntity, MultipartFile avatar) throws Exception;

    void saveProductImage(ProductEntity productEntity, MultipartFile image) throws Exception;

    void updateProductImage(ProductEntity productEntity, MultipartFile image) throws Exception;
}
