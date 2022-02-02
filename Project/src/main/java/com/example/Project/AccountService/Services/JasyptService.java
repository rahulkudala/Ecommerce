package com.example.Project.AccountService.Services;

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JasyptService {

    PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
    SimpleStringPBEConfig config = new SimpleStringPBEConfig();

    String key = "myfirstproject";

    public String encrypt(String password){

        config.setPassword(key);
        config.setPoolSize(10);

        encryptor.setConfig(config);

        String encrypted = encryptor.encrypt(password);

        return encrypted;
    }


    public String decrypt(String password){

        config.setPassword(key);

        config.setPoolSize(10);

        encryptor.setConfig(config);

        String decrypted = encryptor.decrypt(password);

        return decrypted;
    }
}