package com.example.Project.AccountService.Models;

import lombok.Data;

import javax.validation.constraints.Email;

@Data
public class LoginModel{

    @Email(message = "E-mail should be in proper format")
    private String email;
    private String password;

}
