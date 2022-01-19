package com.example.Project.AccountService.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "Customer_Address")
public class CustomerAddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String line1;
    @Column
    private String line2;
    @Column
    private Integer postalCode;
    @Column
    private String state;
    @Column
    private String city;
    @Column
    private boolean shippingAddress;
    @Column
    private boolean billingAddress;


    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinColumn(name = "register_entity_cust_id", referencedColumnName = "ID")
    private RegisterEntity registerEntity;


}
