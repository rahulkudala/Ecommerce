package com.example.Project.CartService.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartModel {

    private String customerEmail;
    private String orderCode;
    private Integer skuCode;
    private Integer quantity;

}
