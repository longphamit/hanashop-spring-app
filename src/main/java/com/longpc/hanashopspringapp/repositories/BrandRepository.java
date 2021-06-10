package com.longpc.hanashopspringapp.repositories;

import com.longpc.hanashopspringapp.entities.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<BrandEntity,String> {
}
