package com.longpc.hanashopspringapp.repositories;

import com.longpc.hanashopspringapp.entities.ImageProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<ImageProductEntity,String> {
}
