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

    @PostMapping("/add-inventory")
    public String inventoryAdding(@RequestBody InventoryModel inventoryModel){
        return inventoryService.addInventory(inventoryModel);
    }

    @GetMapping("/get-inventory")
    public List<InventoryModel> displayingallInventories(){

        return inventoryService.getInventories();
    }

    @PutMapping("/update-inventory/{skuCode}")
    public String updatingInventory(@RequestBody InventoryModel inventoryModel, @PathVariable Integer skuCode){

        return inventoryService.updateInventory(inventoryModel,skuCode);
    }

    /*@GetMapping("/getTest")
    public List<InventoryModel> testGet(){

        return inventoryService.getTest();
    }*/

}
