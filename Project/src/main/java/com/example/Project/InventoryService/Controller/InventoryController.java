package com.example.Project.InventoryService.Controller;

import com.example.Project.InventoryService.Models.InventoryModel;
import com.example.Project.InventoryService.Service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InventoryController {

    @Autowired
    InventoryService inventoryService;

    @PostMapping("/add-inventory/{skucode}")
    public String inventoryAdding(@RequestBody InventoryModel inventoryModel, @PathVariable Integer skucode){
        return inventoryService.addInventory(inventoryModel,skucode);
    }

    @GetMapping("/get-inventory")
    public List<InventoryModel> displayingallInventories(){

        return inventoryService.getInventories();
    }

    @GetMapping("/getTest")
    public List<InventoryModel> testGet(){

        return inventoryService.getTest();
    }

}
