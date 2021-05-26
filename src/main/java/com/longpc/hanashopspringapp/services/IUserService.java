package com.longpc.hanashopspringapp.services;

import com.longpc.hanashopspringapp.dto.UserSearchParamDTO;
import com.longpc.hanashopspringapp.entities.UserEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IUserService {
    UserEntity loginByUsernameOrEmail(String searchString, String password) throws Exception;

    UserEntity loginByEmail(String email) throws Exception;

    boolean register();

    boolean createUser(UserEntity userEntity, MultipartFile avatar) throws Exception;

    List<UserEntity> getUsers(UserSearchParamDTO userSearchParamDTO) throws Exception;

    UserEntity getUserDetail() throws Exception;

    boolean blockUser() throws Exception;

    boolean updateUser(UserEntity userEntity,MultipartFile avatar) throws Exception;
}
