package com.example.Project.AccountService;

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;

public class EncryptionTest {

    public static void main(String args[]){

        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();

        String key = "myfirstproject";

        encryptor.setPoolSize(10);

        config.setPassword(key);

        encryptor.setConfig(config);

        String message = "Hi & Hello!";
        System.out.println("================================");
        String encrypted = encryptor.encrypt(message);

        System.out.println(encrypted);

        System.out.println("================================");
        System.out.println(encryptor.decrypt(encrypted));
        System.out.println(encryptor.decrypt("q2fZd+Q2K1hS8004kn9+wfwtr23h8IR1"));
        System.out.println(encryptor.decrypt("sw3N510dQLmvRdr8Y3CAxvMKtemaVeUi"));





    }

}
