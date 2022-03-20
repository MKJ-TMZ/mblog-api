package com.mtcode.mblogapi.util;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author TangMingZhang
 * @date 2022/3/20
 */
class HashUtilsTest {

    private static final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Test
    void getBC() {
        System.out.println(bCryptPasswordEncoder.encode("e10adc3949ba59abbe56e057f20f883e"));
    }
}