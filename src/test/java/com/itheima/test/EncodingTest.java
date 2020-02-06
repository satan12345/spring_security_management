package com.itheima.test;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncodingTest {

    public static void main(String[] argsl) {
        BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode("123"));
    }
}
