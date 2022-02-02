package com.example.Project.Product.Entities;

import com.example.Project.InventoryService.Entities.InventoryEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "Sku_Table")
public class SkuEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column
    private Integer productCode;

    @Column
    private Integer skuCode;

    @Column
    private String size;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "Product_Sku")
    private ProductEntity productEntity;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Price_Sku")
    private PriceEntity priceEntity;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "inventory_sku")
    private InventoryEntity inventoryEntity;

}