package com.example.Project.CartService.Repository;

import com.example.Project.CartService.Entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<CartEntity,Integer> {


}
