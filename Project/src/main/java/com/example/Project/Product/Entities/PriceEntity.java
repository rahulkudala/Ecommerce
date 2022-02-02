package com.example.Project.Product.Entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Price_Table")
public class PriceEntity {

//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
//    private Integer id;

    @Id
    @Column
    private Integer skuCode;
    @Column
    private double price;
    @Column
    private String currency;



}

