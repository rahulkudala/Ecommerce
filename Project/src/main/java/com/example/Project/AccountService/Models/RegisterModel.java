package com.example.Project.AccountService.Models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class RegisterModel {

    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Long phoneNo;
    private List<CustomerAddressModel> customerAddress;

    public RegisterModel(String firstName, String lastName, String email, String password, Long phoneNo) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phoneNo = phoneNo;
    }

    public RegisterModel(Integer id, String firstName, String lastName, String email, String password, Long phoneNo, List<CustomerAddressModel> customerAddress) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phoneNo = phoneNo;
        this.customerAddress = customerAddress;
    }

    public RegisterModel(Integer id, String firstName, String lastName, String email, String password, Long phoneNo) {

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phoneNo = phoneNo;
    }
}
