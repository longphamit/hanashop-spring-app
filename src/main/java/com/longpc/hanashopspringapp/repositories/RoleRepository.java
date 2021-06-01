package com.longpc.hanashopspringapp.repositories;

import com.longpc.hanashopspringapp.entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity,String> {
}
