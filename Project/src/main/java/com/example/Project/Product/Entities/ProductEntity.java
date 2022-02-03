package com.example.Project.Product.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "Product_Table")
public class ProductEntity {

    @Id
    @Column

    private Integer productCode;

    @Column
    private String productName;

    @Column
    private String description;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "productEntity")
//    @JoinTable(name = "Customer_Sku", joinColumns = { @JoinColumn(name = "Cust_id")}, inverseJoinColumns = {@JoinColumn(name = "Sku_id")})
    @JsonIgnoreProperties("productEntity")
    private List<SkuEntity> skuEntityList;

}
