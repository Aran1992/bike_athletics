package com.h5game;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author qss
 * @date 2018/12/3
 */
public class Test {
    public static void main(String[] args) {
        pwd("buzhidaoa");
    }

    private static String pwd(String pwd) {
        String encode = new BCryptPasswordEncoder().encode(pwd);
        System.out.println("encode = " + encode);
        return encode;
    }
}
