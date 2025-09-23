package com.algohire.backend.util;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;

public class KeyGeneratorUtil {

    public static void main(String[] args) {
        // Generate a secure random 256-bit key for HS256
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

        // Print the key as Base64 string (store this in application.properties)
        System.out.println("JWT Secret Key (Base64): " + Encoders.BASE64.encode(key.getEncoded()));
    }
}
