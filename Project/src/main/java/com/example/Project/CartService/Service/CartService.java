package com.example.Project.CartService.Service;


import com.example.Project.AccountService.Entities.RegisterEntity;
import com.example.Project.AccountService.Repositories.RegisterRepository;
import com.example.Project.CartService.Entity.CartEntity;
import com.example.Project.CartService.Model.CartModel;
import com.example.Project.Product.Repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class CartService {


    @Autowired
    RegisterRepository registerRepository;

    @Autowired
    PriceRepository priceRepository;

    public String addToCart(CartModel cartModel, String email) {

        Optional<RegisterEntity> registerEntity = Optional.ofNullable(registerRepository.findByEmail(email));

        if(registerEntity.isPresent()) {
            CartEntity cartEntity = new CartEntity();

            cartEntity.setCustomerEmail(email);

            String orderID = UUID.randomUUID().toString();
            cartEntity.setOrderCode(orderID);
            cartEntity.setSkuCode(cartModel.getSkuCode());
            cartEntity.setQuantity(cartModel.getQuantity());

            registerEntity.get().getCartEntityList().add(cartEntity);
            registerRepository.save(registerEntity.get());

            return "added";
        }
        else
            return "Customer Not Found";
    }

    public String viewCart(String email) {

        AtomicReference<String> billString = new AtomicReference<>("");

        AtomicReference<Double> fullTotal = new AtomicReference<>(0.0);

        List<CartEntity> cartEntity  = registerRepository.findByEmail(email).getCartEntityList();

        if(!(cartEntity.isEmpty())) {
            cartEntity.stream().forEach(x -> {

                Integer skuCode = x.getSkuCode();
                Integer quantity = x.getQuantity();
                Double rate = priceRepository.findBySkuCode(x.getSkuCode()).getPrice();
                Double total = rate * quantity;
                billString.set(billString +
                        "\nSkuCode: " + skuCode +
                        "\nPrice: " + rate +
                        "\nQuantity: " + quantity +
                        "\nSubTotal: " + total +"\n");

                fullTotal.updateAndGet(d -> d + fullTotal.get());
            });
            billString.set(billString + "\nTotal : " + fullTotal);
            return billString.toString();
        }
        return "Error - User did not added anything to Cart";
    }
}
