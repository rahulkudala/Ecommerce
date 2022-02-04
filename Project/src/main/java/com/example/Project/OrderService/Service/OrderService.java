package com.example.Project.OrderService.Service;

import com.example.Project.AccountService.Entities.RegisterEntity;
import com.example.Project.AccountService.Models.CustomerAddressModel;
import com.example.Project.AccountService.Repositories.RegisterRepository;
import com.example.Project.InventoryService.Models.InventoryModel;
import com.example.Project.InventoryService.Service.InventoryService;
import com.example.Project.OrderService.Entity.OrderEntity;
import com.example.Project.OrderService.Entity.ShippingAddressEntity;
import com.example.Project.OrderService.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    RegisterRepository registerRepository;

    @Autowired
    InventoryService inventoryService;

    @Autowired
    OrderRepository orderRepository;

    public String placeOrder(@NotNull CustomerAddressModel addressModel, String email) {

        RegisterEntity registerEntity = registerRepository.findByEmail(email);

        if(registerEntity!=null) {
            ShippingAddressEntity shippingAddressEntity = new ShippingAddressEntity();

            shippingAddressEntity.setLine1(addressModel.getLine1());
            shippingAddressEntity.setLine2(addressModel.getLine2());
            shippingAddressEntity.setState(addressModel.getState());
            shippingAddressEntity.setCity(addressModel.getCity());
            shippingAddressEntity.setPinCode(addressModel.getPostalCode());

            OrderEntity orderEntity = new OrderEntity();

            orderEntity.setOrderStatus("PLACED");
            orderEntity.setOrderCode(UUID.randomUUID().toString());
            orderEntity.setShippingAddressEntity(shippingAddressEntity);

            registerEntity.getOrderEntityList().add(orderEntity);

            registerRepository.save(registerEntity);


            registerEntity.getCartEntityList().forEach(a -> {

                InventoryModel inventoryModel = new InventoryModel();
                inventoryModel.setQuantityAvailable(a.getQuantity());
                inventoryModel.setSkuCode(a.getSkuCode());
                inventoryService.reduceInventory(inventoryModel);
            });

            return "Order Placed";
        }
        else
            return "Customer not present";
    }
}
