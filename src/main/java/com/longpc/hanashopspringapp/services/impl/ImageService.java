package com.longpc.hanashopspringapp.services.impl;

import com.longpc.hanashopspringapp.constant.ImageConstant;
import com.longpc.hanashopspringapp.constant.LoggerConstant;
import com.longpc.hanashopspringapp.entities.ImageProductEntity;
import com.longpc.hanashopspringapp.entities.ProductEntity;
import com.longpc.hanashopspringapp.entities.UserEntity;
import com.longpc.hanashopspringapp.repositories.ImageRepository;
import com.longpc.hanashopspringapp.repositories.ProductRepository;
import com.longpc.hanashopspringapp.repositories.UserRepository;
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
import java.util.List;
import java.util.UUID;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

@Service
public class ImageService implements IImageService {
    private Logger LOGGER = LoggerFactory.getLogger(getClass());
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ImageRepository imageRepository;

    @Override
    public void updateUserImage(UserEntity userEntity, MultipartFile avatar) throws Exception {
        if (avatar != null) {
            Path userFolder = Paths.get(ImageConstant.USER_FOLDER + userEntity.getUsername()).toAbsolutePath().normalize();
            if (!Files.exists(userFolder)) {
                Files.createDirectories(userFolder);
                LOGGER.info(LoggerConstant.createMessageLog("Avatar folder Create"));
            }
            Files.deleteIfExists(Paths.get(userFolder + userEntity.getUsername() + "." + ImageConstant.JPG_EXTENSION));
            Files.copy(avatar.getInputStream(), userFolder.resolve(userEntity.getUsername() + "." + ImageConstant.JPG_EXTENSION), REPLACE_EXISTING);
            userEntity.setAvatar(setProfileImageUrl(userEntity.getUsername()));
        }
        userRepository.save(userEntity);
        LOGGER.info(LoggerConstant.createMessageLog("Avatar of user saved"));
    }

    @Override
    public void saveUserImage(UserEntity userEntity, MultipartFile avatar) throws Exception {
        if (avatar == null) {
            userEntity.setAvatar(ImageConstant.TEMP_PROFILE_IMAGE_BASE_URL + userEntity.getUsername());
        } else {
            Path userFolder = Paths.get(ImageConstant.USER_FOLDER + userEntity.getUsername()).toAbsolutePath().normalize();
            if (!Files.exists(userFolder)) {
                Files.createDirectories(userFolder);
                LOGGER.info(LoggerConstant.createMessageLog("Avatar folder Create"));
            }
            Files.deleteIfExists(Paths.get(userFolder + userEntity.getUsername() + "." + ImageConstant.JPG_EXTENSION));
            Files.copy(avatar.getInputStream(), userFolder.resolve(userEntity.getUsername() + "." + ImageConstant.JPG_EXTENSION), REPLACE_EXISTING);
            userEntity.setAvatar(setProfileImageUrl(userEntity.getUsername()));
        }
        userRepository.save(userEntity);
        LOGGER.info(LoggerConstant.createMessageLog("Avatar of user saved"));
    }

    @Override
    public void saveProductImage(ProductEntity productEntity, List<MultipartFile> images) throws Exception {


        if (images != null && images.size() > 0) {
            Path productFolder = Paths.get(ImageConstant.PRODUCT_FOLDER + productEntity.getId());
            if (!Files.exists(productFolder)) {
                Files.createDirectories(productFolder);
                LOGGER.info(LoggerConstant.createMessageLog("Product Image Folder Create"));
                Files.deleteIfExists(Paths.get(productFolder + productEntity.getId() + "." + ImageConstant.JPG_EXTENSION));
                for (MultipartFile image : images) {
                    Files.copy(image.getInputStream(), productFolder.resolve(image.getOriginalFilename() + "." + ImageConstant.JPG_EXTENSION), REPLACE_EXISTING);
                    ImageProductEntity imageProductEntity = new ImageProductEntity();
                    imageProductEntity.setId(UUID.randomUUID().toString());
                    imageProductEntity.setProductId(productEntity.getId());
                    imageProductEntity.setPath(setImageProductUrl(productEntity.getId(), image.getOriginalFilename()));
                    imageRepository.save(imageProductEntity);
                    productRepository.save(productEntity);
                    LOGGER.info(LoggerConstant.createMessageLog("Image of Product saved " + productEntity.getId()));
                }
            }
        }
    }

    public String saveCkfinderImage(MultipartFile image) throws Exception {
        String path = ImageConstant.PRODUCT_CKFINDER_IMAGE_FOLDER + image.getOriginalFilename() + "." + ImageConstant.JPG_EXTENSION;
        if (image != null) {
            Path productCKFinderPath = Paths.get(path);
            if (!Files.exists(productCKFinderPath)) {
                Files.createDirectories(productCKFinderPath);
                LOGGER.info(LoggerConstant.createMessageLog("Product Image Folder Create"));
                Files.deleteIfExists(Paths.get(path));
                Files.copy(image.getInputStream(), productCKFinderPath, REPLACE_EXISTING);
                return setCKfinderImageUrl(image.getOriginalFilename());
            }
            Files.deleteIfExists(productCKFinderPath);
            Files.copy(image.getInputStream(), productCKFinderPath, REPLACE_EXISTING);
            return setCKfinderImageUrl(image.getOriginalFilename());
        }
        return null;
    }

    @Override
    public void updateProductImage(ProductEntity productEntity, MultipartFile image) throws Exception {

    }

    private String setProfileImageUrl(String username) {
        return ServletUriComponentsBuilder.fromCurrentContextPath().path(ImageConstant.USER_IMAGE_PATH + username + "/" + username + "." + ImageConstant.JPG_EXTENSION).toUriString();
    }

    private String setImageProductUrl(String productId, String imageName) {
        return ServletUriComponentsBuilder.fromCurrentContextPath().path(ImageConstant.PRODUCT_IMAGE_PATH + productId + "/" + imageName + "." + ImageConstant.JPG_EXTENSION).toUriString();
    }

    private String setCKfinderImageUrl(String imageName) {
        return ServletUriComponentsBuilder.fromCurrentContextPath().path(ImageConstant.PRODUCT_CKFINDER_IMAGE_PATH + "/" + imageName + "." + ImageConstant.JPG_EXTENSION).toUriString();
    }

}
