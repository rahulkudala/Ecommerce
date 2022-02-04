package com.example.Project.OrderService.Model;

import com.example.Project.AccountService.Entities.RegisterEntity;
import com.example.Project.OrderService.Entity.BillingAddressEntity;
import com.example.Project.OrderService.Entity.ShippingAddressEntity;

public class OrderModel {

    private ShippingAddressEntity shippingAddressEntity;

    private BillingAddressEntity billingAddressEntity;

    private RegisterEntity registerEntity;

    private String orderStatus;
}
