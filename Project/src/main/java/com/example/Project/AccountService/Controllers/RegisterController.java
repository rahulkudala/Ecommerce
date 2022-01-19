package com.example.Project.AccountService.Controllers;


import com.example.Project.AccountService.Models.CustomerAddressModel;
import com.example.Project.AccountService.Models.RegisterModel;
import com.example.Project.AccountService.Services.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @PostMapping("/register-customer")
    public String addCust(@RequestBody RegisterModel registerModel){

        return registerService.addCustomer(registerModel);

    }


    @GetMapping("/get-customers")
    public List getCust(){

        return registerService.getAll();
    }


    @PatchMapping("/add-customer-address/{email}")
    public String addAddress(@RequestBody CustomerAddressModel customerAddress, @PathVariable String email){

        return registerService.addCustomerAddress(customerAddress,email);

    }

    @GetMapping("/get-customer-details/{id}")
    public RegisterModel custdetails(@PathVariable Integer id){

        return registerService.getCustAdress(id);
    }

}