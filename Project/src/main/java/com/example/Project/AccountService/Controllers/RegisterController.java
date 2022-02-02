package com.example.Project.AccountService.Controllers;

import com.example.Project.AccountService.Models.CustomerAddressModel;
import com.example.Project.AccountService.Models.RegisterModel;
import com.example.Project.AccountService.Services.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@Validated
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


    @PostMapping("/add-customer-address")
    public String addAddress(@RequestBody CustomerAddressModel customerAddress, @RequestHeader String email, @RequestHeader String password){

        return registerService.addCustomerAddress(customerAddress,email,password);

    }

    @GetMapping("/get-customer-address-by-id/{id}")
    public List custdetails(@PathVariable Integer id){

        return registerService.getCustAddress(id);
    }

    @GetMapping("/get-customer-address-by-email/{email}")
    public List custdetails(@PathVariable String email){

        return registerService.getCustAddress(email);
    }

    @PostMapping("/login")
    public String login(@RequestHeader String email, @RequestHeader String password)
    {
        return registerService.login(email,password);
    }
}