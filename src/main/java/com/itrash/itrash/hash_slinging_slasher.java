package com.itrash.itrash;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Random;

public class hash_slinging_slasher {
    public static String generateSalt() {
        Random random = new Random(); // TODO shared?
        byte[] bytes = new byte[128];
        random.nextBytes(bytes);
        return Base64.getEncoder().encodeToString(bytes);
    }


    public static String hash(String password, String salt) {
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        byte[] passwordBytes = password.getBytes(StandardCharsets.UTF_8);
        byte[] saltBytes = Base64.getDecoder().decode(salt);
        byte[] bytes = new byte[password.length() + saltBytes.length];
        System.arraycopy(passwordBytes, 0, bytes, 0, passwordBytes.length);
        System.arraycopy(saltBytes, 0, bytes, passwordBytes.length, saltBytes.length);
        return Base64.getEncoder().encodeToString(digest.digest(bytes));
    }

    public static boolean passwordMatches(String password, String hash, String salt) {
        return hash(password, salt).equals(hash);
    }
}
