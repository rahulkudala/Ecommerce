package com.example.Project.AccountService.Repositories;


import com.example.Project.AccountService.Entities.CustomerAddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerAddressRepository extends JpaRepository<CustomerAddressEntity,Integer> {


}
