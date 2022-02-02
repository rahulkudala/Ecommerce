package com.example.Project.Product.Models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductModel {

    private Integer productCode;
    private String productName;
    private String description;

    private List<SkuModel> sku;

}
