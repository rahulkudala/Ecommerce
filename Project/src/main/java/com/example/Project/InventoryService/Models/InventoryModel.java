package com.example.Project.InventoryService.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryModel {
    private Integer skuCode;
    private Integer quantityAvailable;
}