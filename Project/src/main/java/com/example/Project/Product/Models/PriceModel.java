package com.example.Project.Product.Models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PriceModel {

    private Integer skuCode;
    private double price;
    private String currency;

}
