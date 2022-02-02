package com.example.Project.Product.Repository;

import com.example.Project.Product.Entities.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceRepository extends JpaRepository<PriceEntity,Integer> {

    PriceEntity findBySkuCode(Integer skuCode);

}
