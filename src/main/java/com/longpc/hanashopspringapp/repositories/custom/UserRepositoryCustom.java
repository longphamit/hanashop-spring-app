package com.longpc.hanashopspringapp.repositories.custom;

import com.longpc.hanashopspringapp.dto.UserSearchParamDTO;
import com.longpc.hanashopspringapp.entities.UserEntity;

import java.util.List;

public interface UserRepositoryCustom {
    List<UserEntity> search(UserSearchParamDTO userSearchParamDTO) throws Exception;
}
