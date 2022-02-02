package com.example.Project.Product.Controller;

import com.example.Project.Product.Models.PriceModel;
import com.example.Project.Product.Models.ProductModel;
import com.example.Project.Product.Models.SkuModel;
import com.example.Project.Product.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.GeneratedValue;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/add-product")
    public String addProduct(@RequestBody ProductModel productModel){

        return productService.addProducts(productModel);
    }

    @PostMapping("/add-sku")
    public String addSku(@RequestBody SkuModel skuModel)
    {
        return productService.addSku(skuModel);
    }

    @PostMapping("/add-price")
    public String addPrice(@RequestBody PriceModel priceModel){
        return productService.addPrice(priceModel);
    }

    @GetMapping("/get-products")
    public List getAllProducts(){

        return productService.getAllProducts();
    }
}
