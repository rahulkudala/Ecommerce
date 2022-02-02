package com.example.Project.Product.Repository;

import com.example.Project.Product.Entities.SkuEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkuRepository extends JpaRepository<SkuEntity,Integer> {

    SkuEntity findBySkuCode(Integer skuCode);

}