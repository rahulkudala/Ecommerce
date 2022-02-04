package com.example.Project.AccountService.Entities;

import com.example.Project.CartService.Entity.CartEntity;
import com.example.Project.OrderService.Entity.OrderEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "Customer_Details")
public class RegisterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Cust_ID")
    private Integer id;
    @Column(name = "FirstName")
    private String firstName;
    @Column(name = "LastName")
    private String lastName;
    @Column(name = "Email_Id")
    private String email;
    @Column(name = "Passcode_Enc")
    private String password;
    @Column(name = "Phone_Number")
    private Long phoneNo;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER,mappedBy = "registerEntity")
    private List<CustomerAddressEntity> customerAddressEntities;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,orphanRemoval = true)
    private List<CartEntity> cartEntityList;

    @OneToMany
    private List<OrderEntity> orderEntityList;


}
