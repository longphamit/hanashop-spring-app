package com.longpc.hanashopspringapp.services.impl;

import com.longpc.hanashopspringapp.constant.ImageConstant;
import com.longpc.hanashopspringapp.constant.LoggerConstant;
import com.longpc.hanashopspringapp.entities.UserEntity;
import com.longpc.hanashopspringapp.repositories.IUserRepository;
import com.longpc.hanashopspringapp.services.IImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
@Service
public class ImageService implements IImageService {
    private Logger LOGGER= LoggerFactory.getLogger(getClass());
    @Autowired
    IUserRepository userRepository;
    @Override
    public void updateUserImage(UserEntity userEntity, MultipartFile avatar) throws Exception {
        if(avatar!=null){
            Path userFolder= Paths.get(ImageConstant.USER_FOLDER+userEntity.getUsername()).toAbsolutePath().normalize();
            if(!Files.exists(userFolder)){
                Files.createDirectories(userFolder);
                LOGGER.info(LoggerConstant.createMessageLog("Avatar folder Create"));
            }
            Files.deleteIfExists(Paths.get(userFolder+userEntity.getUsername()+"."+ ImageConstant.JPG_EXTENSION));
            Files.copy(avatar.getInputStream(),userFolder.resolve(userEntity.getUsername()+"."+ImageConstant.JPG_EXTENSION),REPLACE_EXISTING);
            userEntity.setAvatar(setProfileImageUrl(userEntity.getUsername()));
        }
        userRepository.save(userEntity);
        LOGGER.info(LoggerConstant.createMessageLog("Avatar of user saved"));
    }

    @Override
    public void saveUserImage(UserEntity userEntity, MultipartFile avatar) throws Exception {
        if(avatar==null){
            userEntity.setAvatar(ImageConstant.TEMP_PROFILE_IMAGE_BASE_URL+userEntity.getUsername());
        }else{
            Path userFolder= Paths.get(ImageConstant.USER_FOLDER+userEntity.getUsername()).toAbsolutePath().normalize();
            if(!Files.exists(userFolder)){
                Files.createDirectories(userFolder);
                LOGGER.info(LoggerConstant.createMessageLog("Avatar folder Create"));
            }
            Files.deleteIfExists(Paths.get(userFolder+userEntity.getUsername()+"."+ ImageConstant.JPG_EXTENSION));
            Files.copy(avatar.getInputStream(),userFolder.resolve(userEntity.getUsername()+"."+ImageConstant.JPG_EXTENSION),REPLACE_EXISTING);
            userEntity.setAvatar(setProfileImageUrl(userEntity.getUsername()));
        }
        userRepository.save(userEntity);
        LOGGER.info(LoggerConstant.createMessageLog("Avatar of user saved"));
    }

    private String setProfileImageUrl(String username) {
        return ServletUriComponentsBuilder.fromCurrentContextPath().path(ImageConstant.USER_IMAGE_PATH+username+"/"+username+"."+ImageConstant.JPG_EXTENSION).toUriString();
    }

}
