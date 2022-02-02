package com.example.Project.Product.Repository;

import com.example.Project.Product.Entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity,Integer> {

    ProductEntity findByProductCode(Integer productCode);

}
