package com.longpc.hanashopspringapp.repositories;

import com.longpc.hanashopspringapp.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserRepository extends JpaRepository<UserEntity,String> {
    @Query("FROM UserEntity u WHERE u.username = :searchString or u.email = :searchString ")
    public UserEntity findByUsernameOrEmail(@Param("searchString") String searchString);
    public UserEntity findByEmail(String email);
}
