package com.example.Project.AccountService.Services;

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.stereotype.Service;

@Service
public class JasyptService {

    public String encrypt(String password){

        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        encryptor.setPoolSize(10);
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();

        String key = "myfirstproject";
        config.setPassword(key);

        encryptor.setConfig(config);

        String message = "Hi & Hello!";

        String encrypted = encryptor.encrypt(password);

        return encrypted;
    }
}
