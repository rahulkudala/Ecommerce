package com.example.Project.CartService.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CartEntity {

    private String customerEmail;
    @Id
    private Integer orderCode;
    private Integer skuCode;
    private Integer quantity;

}
