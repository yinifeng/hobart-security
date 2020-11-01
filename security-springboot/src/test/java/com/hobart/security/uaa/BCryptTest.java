package com.hobart.security.uaa;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class BCryptTest {
    public static void main(String[] args) {
        String hashpw = BCrypt.hashpw("secret", BCrypt.gensalt());
        System.out.println(hashpw);
    }
}
