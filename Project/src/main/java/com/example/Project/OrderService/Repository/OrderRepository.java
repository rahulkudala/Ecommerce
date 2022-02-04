package com.example.Project.OrderService.Repository;

import com.example.Project.OrderService.Entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {

    OrderEntity findByOrderCode(String orderCode);
}
