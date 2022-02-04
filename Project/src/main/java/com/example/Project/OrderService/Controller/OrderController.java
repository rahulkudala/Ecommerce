package com.example.Project.OrderService.Controller;

import com.example.Project.AccountService.Models.CustomerAddressModel;
import com.example.Project.OrderService.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {

    @Autowired
    OrderService orderService;


    @PostMapping("/order")
    public String orderPlacing(@RequestHeader String email, @RequestBody CustomerAddressModel addressModel)
    {
        return orderService.placeOrder(addressModel,email);
    }

}
