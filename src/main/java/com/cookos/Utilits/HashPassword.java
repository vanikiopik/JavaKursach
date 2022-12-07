package com.cookos.Utilits;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class HashPassword {

    public static byte[] getHash(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException{
        var digest = MessageDigest.getInstance("SHA-512");
        digest.reset();
        return digest.digest(password.getBytes(StandardCharsets.UTF_8));
    }
}
