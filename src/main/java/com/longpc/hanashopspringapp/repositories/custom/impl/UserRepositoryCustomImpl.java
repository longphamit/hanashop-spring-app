package com.longpc.hanashopspringapp.repositories.custom.impl;

import com.longpc.hanashopspringapp.dto.UserSearchParamDTO;
import com.longpc.hanashopspringapp.entities.UserEntity;
import com.longpc.hanashopspringapp.repositories.custom.UserRepositoryCustom;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UserRepositoryCustomImpl implements UserRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<UserEntity> search(UserSearchParamDTO userSearchParamDTO) throws Exception {
        StringBuilder sql= new StringBuilder("FROM UserEntity e, RoleEntity r where 1=1 and e.roleId=r.id ");
        if(StringUtils.isNotEmpty(userSearchParamDTO.getUsername())){
            sql.append(" and e.username like concat('%',:username,'%') ");
        }
        if(StringUtils.isNotEmpty(userSearchParamDTO.getFullname())){
            sql.append(" and e.fullname like :fullname");
        }
        if(StringUtils.isNotEmpty(userSearchParamDTO.getRoleId())){
            sql.append(" and r.id =:roleId ");
        }
        Query query= entityManager.createQuery(sql.toString());
        if(StringUtils.isNotEmpty(userSearchParamDTO.getUsername())){
            query.setParameter("username",userSearchParamDTO.getUsername());
        }
        if(StringUtils.isNotEmpty(userSearchParamDTO.getFullname())){
            query.setParameter("fullname",userSearchParamDTO.getFullname());
        }
        if(StringUtils.isNotEmpty(userSearchParamDTO.getRoleId())){
            query.setParameter("roleId",userSearchParamDTO.getRoleId());
        }
        return query.getResultList();
    }
}
