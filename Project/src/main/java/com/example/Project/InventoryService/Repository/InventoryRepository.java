package com.example.Project.InventoryService.Repository;

import com.example.Project.InventoryService.Entities.InventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<InventoryEntity,Integer> {

    InventoryEntity findBySkuCode(Integer skuCode);

}
