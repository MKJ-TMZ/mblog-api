package com.mtcode.mblogapi.util;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.mtcode.mblogapi.entity.User;
import com.mtcode.mblogapi.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author TangMingZhang
 * @date 2022/3/29
 */
public class Auth {

    public static Authentication getAuth() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public static Long getUserId() {
        UserService userService = SpringContextUtil.getBean(UserService.class);
        String username = getAuth().getName();
        User user = userService.getOne(Wrappers.lambdaQuery(User.class).eq(User::getUsername, username));
        if (user != null) {
            return user.getId();
        } else {
            return null;
        }
    }

}
