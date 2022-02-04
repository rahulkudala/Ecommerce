package com.example.Project.OrderService.Entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class BillingAddressEntity {

    @Id
    private Integer id;

    private String line1;
    private String line2;
    private int pinCode;
    private String state;
    private String city;
}
