package com.example.Project.CartService.Controller;

import com.example.Project.CartService.Model.CartModel;
import com.example.Project.CartService.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CartController {

    @Autowired
    CartService cartService;

    @PostMapping("/add-to-cart")
    public String addingtoCart(@RequestBody CartModel cartModel, @RequestHeader String email){

        return cartService.addToCart(cartModel,email);

    }

    @GetMapping("/view-cart")
    public String showCart(@RequestHeader String email){

        return cartService.viewCart(email);
    }



}
