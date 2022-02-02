package com.example.Project.AccountService.Repositories;

import com.example.Project.AccountService.Entities.LoginEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepository extends JpaRepository<LoginEntity,String> {
}
