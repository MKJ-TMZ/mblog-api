package com.mtcode.mblogapi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mtcode.mblogapi.entity.User;
import com.mtcode.mblogapi.mapper.UserMapper;
import com.mtcode.mblogapi.service.UserService;
import com.mtcode.mblogapi.util.HashUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author TangMingZhang
 * @date 2022/3/15
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService, UserDetailsService {

    @Override
    public User findUserByUsernameAndPassword(String username, String password) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername, username);
        User user = baseMapper.selectOne(wrapper);

        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        if (!HashUtils.matchBC(password, user.getPassword())) {
            throw new UsernameNotFoundException("密码错误");
        }
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername, username);
        User user = baseMapper.selectOne(wrapper);
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        return user;
    }
}
