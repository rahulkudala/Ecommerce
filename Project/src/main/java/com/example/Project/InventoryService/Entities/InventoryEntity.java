package com.example.Project.InventoryService.Entities;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class InventoryEntity {

    @Id
    private Integer skuCode;

    private Integer quantityAvailable;
}
