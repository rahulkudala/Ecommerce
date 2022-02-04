package com.example.Project.OrderService.Entity;


import com.example.Project.AccountService.Entities.RegisterEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OrderEntity {

        @Id
        private String orderCode;
        @OneToOne(cascade = CascadeType.ALL)
        private ShippingAddressEntity shippingAddressEntity;

        @OneToOne(cascade = CascadeType.ALL)
        private BillingAddressEntity billingAddressEntity;

        @ManyToOne(cascade = CascadeType.ALL)
        private RegisterEntity registerEntity;

        private String orderStatus;

}
