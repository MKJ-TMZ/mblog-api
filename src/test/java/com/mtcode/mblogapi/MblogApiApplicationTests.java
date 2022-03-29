package com.mtcode.mblogapi;

import com.mtcode.mblogapi.entity.User;
import com.mtcode.mblogapi.mapper.UserMapper;
import com.mtcode.mblogapi.util.HashUtils;
import com.mtcode.mblogapi.util.SpringContextUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@SpringBootTest
class MblogApiApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void createUser() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        UserMapper userMapper = SpringContextUtil.getBean(UserMapper.class);
        User user = new User();
        user.setUsername("admin");
        user.setPassword(HashUtils.getBC(getMd5("admin")));
        user.setRole("ROLE_admin");
        userMapper.insert(user);
    }

    public static String getMd5(String text) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] bytes = md5.digest(text.getBytes(StandardCharsets.UTF_8));

        StringBuilder builder = new StringBuilder();

        for (byte aByte : bytes) {
            builder.append(Integer.toHexString((0x000000FF & aByte) | 0xFFFFFF00).substring(6));
        }

        return builder.toString();
    }

}
