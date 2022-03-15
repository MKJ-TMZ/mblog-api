package com.mtcode.mblogapi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mtcode.mblogapi.entity.User;

/**
 * @author TangMingZhang
 * @date 2022/3/15
 */
public interface UserService extends IService<User> {
    User findUserByUsernameAndPassword(String username, String password);
}
