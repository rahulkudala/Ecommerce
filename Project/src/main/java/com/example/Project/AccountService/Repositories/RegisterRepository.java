package com.example.Project.AccountService.Repositories;

import com.example.Project.AccountService.Entities.RegisterEntity;
import com.example.Project.AccountService.Models.RegisterModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegisterRepository extends JpaRepository<RegisterEntity, Integer> {

    RegisterEntity findByEmail(String email);
}
