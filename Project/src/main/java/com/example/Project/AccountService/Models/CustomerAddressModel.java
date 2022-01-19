package com.example.Project.AccountService.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerAddressModel {

    private String line1;
    private String line2;   
    private Integer postalCode;
    private String state;
    private String city;
    private boolean shippingAddress;
    private boolean billingAddress;

}
