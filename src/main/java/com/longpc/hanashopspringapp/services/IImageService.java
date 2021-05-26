package com.longpc.hanashopspringapp.services;

import com.longpc.hanashopspringapp.entities.UserEntity;
import org.springframework.web.multipart.MultipartFile;

public interface IImageService {
    void updateUserImage(UserEntity userEntity, MultipartFile avatar) throws Exception;

    void saveUserImage(UserEntity userEntity, MultipartFile avatar) throws Exception;
}
