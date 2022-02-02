package com.example.Project.AccountService.Services;

import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class Authentication {

    int expiry = 1000 * 60 * 1;
    String key = "myfirstproject";

    public String generateToken(String email){

        String encToken = Jwts.builder().setSubject(email)
                .setExpiration(new Date(System.currentTimeMillis() + expiry))
                .signWith(SignatureAlgorithm.HS512,key.getBytes(StandardCharsets.UTF_8))
                .compact();

        return encToken;

    }

    public boolean validateToken(String token){

        boolean value = false;


        return value;
    }


}
