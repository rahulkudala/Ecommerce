package com.example.Project.InventoryService.Service;

import com.example.Project.InventoryService.Entities.InventoryEntity;
import com.example.Project.InventoryService.Repository.InventoryRepository;
import com.example.Project.InventoryService.Models.InventoryModel;
import com.example.Project.Product.Entities.SkuEntity;
import com.example.Project.Product.Repository.ProductRepository;
import com.example.Project.Product.Repository.SkuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryService {

    @Autowired
    SkuRepository skuRepository;
    @Autowired
    ProductRepository productRepository;

    @Autowired
    InventoryRepository inventoryRepository;


    public String  addInventory(InventoryModel inventoryModel){

        Optional<SkuEntity> skuEntity = Optional.ofNullable(skuRepository.findBySkuCode(inventoryModel.getSkuCode()));

//        Optional<ProductEntity> productEntity = Optional.ofNullable(productRepository.findByProductCode(inventoryModel.getSkuCode()));

        InventoryEntity inventoryEntity = new InventoryEntity();
        if(skuEntity.isPresent()){

            inventoryEntity.setSkuCode(inventoryModel.getSkuCode());
            inventoryEntity.setQuantityAvailable(inventoryModel.getQuantityAvailable());

            skuEntity.get().setInventoryEntity(inventoryEntity);
            skuRepository.save(skuEntity.get());
            inventoryRepository.save(inventoryEntity);

        //    skuRepository.save(productEntity.get().getSkuEntityList());



            return "Inventory Updated";
        }
        else {


            inventoryEntity.setSkuCode(inventoryModel.getSkuCode());
            inventoryEntity.setQuantityAvailable(inventoryModel.getQuantityAvailable());

            inventoryRepository.save(inventoryEntity);

            return "Product not Found !!!!\n Inventory added!";
        }
    }

    public String updateInventory(InventoryModel inventoryModel, Integer skuCode){

        InventoryEntity inventoryEntities = inventoryRepository.findBySkuCode(skuCode);

        if(!(inventoryEntities.equals(null))){

            inventoryEntities.setQuantityAvailable(inventoryModel.getQuantityAvailable() + inventoryEntities.getQuantityAvailable());
            inventoryRepository.save(inventoryEntities);

            return " Product sku updated";
        }
        else
            return "No Product found!";

    }

    public String reduceInventory(InventoryModel inventoryModel){

        Integer quantityPresent = inventoryRepository.findBySkuCode(inventoryModel.getSkuCode()).getQuantityAvailable();
        InventoryEntity inventoryEntity = inventoryRepository.findBySkuCode(inventoryModel.getSkuCode());
        SkuEntity skuEntity = skuRepository.findBySkuCode(inventoryModel.getSkuCode());


        inventoryEntity.setSkuCode(inventoryModel.getSkuCode());
        inventoryEntity.setQuantityAvailable(quantityPresent - inventoryModel.getQuantityAvailable());

        skuEntity.setInventoryEntity(inventoryEntity);

        skuRepository.save(skuEntity);


        return "Inventory reduced";
    }

    public List getInventories() {

        return inventoryRepository.findAll();
    }


}