package com.longpc.hanashopspringapp.services.impl;

import com.longpc.hanashopspringapp.constant.UserConstant;
import com.longpc.hanashopspringapp.dto.UserSearchParamDTO;
import com.longpc.hanashopspringapp.entities.UserEntity;
import com.longpc.hanashopspringapp.repositories.UserRepository;
import com.longpc.hanashopspringapp.services.IImageService;
import com.longpc.hanashopspringapp.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class UserService implements IUserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    IImageService iImageService;

    public UserEntity loginByUsernameOrEmail(String searchString, String password) throws Exception {
        UserEntity userEntity = userRepository.findByUsernameOrEmail(searchString);
        if (null != userEntity) {
            if (userEntity.getPassword().equals(password)) {
                return userEntity;
            }
        }
        return null;
    }

    @Override
    public UserEntity loginByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public boolean register() {
        return false;
    }

    @Override
    public boolean createUser(UserEntity userEntity, MultipartFile avatar) throws Exception {
        userEntity.setId(UUID.randomUUID().toString());
        userEntity.setCreateAt(new Timestamp(new Date().getTime()));
        userEntity.setPassword("123@123");
        userEntity.setRoleId("1");
        userEntity.setStatus(UserConstant.Status.ACTIVE.name());
        userEntity.setPoint(0);
        userRepository.save(userEntity);
        iImageService.saveUserImage(userEntity, avatar);
        return true;
    }

    public boolean updateUser(UserEntity userEntity, MultipartFile avatar) throws Exception {
        userEntity.setUpdateAt(new Timestamp(new Date().getTime()));
        userRepository.save(userEntity);
        iImageService.updateUserImage(userEntity, avatar);
        return true;
    }

    public List<UserEntity> getUsers(UserSearchParamDTO userSearchParamDTO) throws Exception {
        List<UserEntity> useLisst = userRepository.search(userSearchParamDTO);
        return useLisst;
    }

    public UserEntity getUserDetail() {
        return null;
    }

    public boolean blockUser() {
        return false;
    }
}
