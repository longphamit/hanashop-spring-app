package com.longpc.hanashopspringapp.repositories;

import com.longpc.hanashopspringapp.entities.ProductEntity;
import com.longpc.hanashopspringapp.repositories.custom.ProductRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity,String>, ProductRepositoryCustom {
}
