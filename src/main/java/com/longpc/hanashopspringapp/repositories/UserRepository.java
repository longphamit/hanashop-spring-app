package com.longpc.hanashopspringapp.repositories;

import com.longpc.hanashopspringapp.entities.UserEntity;
import com.longpc.hanashopspringapp.repositories.custom.UserRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String>, UserRepositoryCustom {

    @Query("FROM UserEntity u WHERE u.username = :searchString or u.email = :searchString ")
    UserEntity findByUsernameOrEmail(@Param("searchString") String searchString);

    UserEntity findByEmail(String email);
}
