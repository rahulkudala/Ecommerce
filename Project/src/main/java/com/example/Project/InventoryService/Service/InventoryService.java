package com.example.Project.InventoryService.Service;

import com.example.Project.InventoryService.Entities.InventoryEntity;
import com.example.Project.InventoryService.Repository.InventoryRepository;
import com.example.Project.InventoryService.Models.InventoryModel;
import com.example.Project.Product.Entities.ProductEntity;
import com.example.Project.Product.Entities.SkuEntity;
import com.example.Project.Product.Repository.ProductRepository;
import com.example.Project.Product.Repository.SkuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InventoryService {

    @Autowired
    SkuRepository skuRepository;
    @Autowired
    ProductRepository productRepository;

    @Autowired
    InventoryRepository inventoryRepository;


    public String addInventory(InventoryModel inventoryModel, Integer skuCode){

        Optional<SkuEntity> skuEntity = Optional.ofNullable(skuRepository.findBySkuCode(skuCode));
        Optional<ProductEntity> productEntity = Optional.ofNullable(productRepository.findByProductCode(inventoryModel.getSkuCode()));

        if(skuEntity.isPresent()){

            InventoryEntity inventoryEntity = new InventoryEntity();

            inventoryEntity.setSkuCode(inventoryModel.getSkuCode());
            inventoryEntity.setQuantityAvailable(inventoryModel.getQuantityAvailable());

            skuEntity.get().setInventoryEntity(inventoryEntity);
            skuRepository.save(skuEntity.get());

        //    skuRepository.save(productEntity.get().getSkuEntityList());

            inventoryRepository.save(inventoryEntity);

            return "Inventory Updated";
        }
        else {
            /*InventoryEntity inventoryEntity = new InventoryEntity();

            inventoryEntity.setSkuCode(skuCode);
            inventoryEntity.setQuantityAvailable(inventoryModel.getQuantityAvailable());

           // skuRepository.save(skuEntity.get());

            inventoryRepository.save(inventoryEntity);*/


//            return "Not added to Inventory";
            return "Product not Found !!!!";
        }
    }

    public String updateInventory(){

        return null;
    }

    public List getInventories() {

        List inventories = skuRepository.findAll().stream().map(i -> i.getInventoryEntity()).collect(Collectors.toList());
                //inventoryRepository.findAll();

        /*List<InventoryModel> list = new ArrayList<>();

        inventoryEntities.stream().forEach(x -> list.add(new InventoryModel(x.getSkuCode(),x.getQuantityAvailable())));*/

        return inventories;
    }

    public List getTest(){

        return inventoryRepository.findAll();
    }
}