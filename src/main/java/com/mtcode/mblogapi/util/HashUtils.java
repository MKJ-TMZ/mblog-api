package com.mtcode.mblogapi.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author TangMingZhang
 * @date 2022/3/14
 */
public class HashUtils {
    private static final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public static String getBC(CharSequence rawPassword) {
        return bCryptPasswordEncoder.encode(rawPassword);
    }

    public static boolean matchBC(CharSequence rawPassword, String encodedPassword) {
        return bCryptPasswordEncoder.matches(rawPassword, encodedPassword);
    }
}
