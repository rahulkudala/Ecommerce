package com.example.Project.InventoryService.Service;

import com.example.Project.InventoryService.Entities.InventoryEntity;
import com.example.Project.InventoryService.Repository.InventoryRepository;
import com.example.Project.InventoryService.Models.InventoryModel;
import com.example.Project.Product.Entities.ProductEntity;
import com.example.Project.Product.Repository.ProductRepository;
import com.example.Project.Product.Repository.SkuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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


    public String addInventory(InventoryModel inventoryModel, Integer skuCode){

        Optional<ProductEntity> productEntity = Optional.ofNullable(productRepository.findByProductCode(inventoryModel.getSkuCode()));

        if(!(productEntity.isPresent())){

            InventoryEntity inventoryEntity = new InventoryEntity();

            inventoryEntity.setSkuCode(inventoryModel.getSkuCode());
            inventoryEntity.setQuantityAvailable(inventoryModel.getQuantityAvailable());

        //    skuRepository.save(productEntity.get().getSkuEntityList());

            inventoryRepository.save(inventoryEntity);

            return "Inventory added";
        }
        else {
            /*InventoryEntity inventoryEntity = new InventoryEntity();

            inventoryEntity.setSkuCode(skuCode);
            inventoryEntity.setQuantityAvailable(inventoryModel.getQuantityAvailable());

           // skuRepository.save(skuEntity.get());

            inventoryRepository.save(inventoryEntity);*/


//            return "Not added to Inventory";
            return "Already Exist";
        }
    }

    public String updateInventory(){

        return null;
    }

    public List<InventoryModel> getInventories() {

        List<InventoryEntity> inventoryEntities = inventoryRepository.findAll();

        List<InventoryModel> list = new ArrayList<>();

        inventoryEntities.stream().forEach(x -> list.add(new InventoryModel(x.getSkuCode(),x.getQuantityAvailable())));

        return list;
    }

    public List getTest(){

        return inventoryRepository.findAll();
    }
}