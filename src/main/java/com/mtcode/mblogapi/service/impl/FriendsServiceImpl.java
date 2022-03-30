package com.mtcode.mblogapi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mtcode.mblogapi.entity.Friends;
import com.mtcode.mblogapi.exception.ParameterException;
import com.mtcode.mblogapi.mapper.FriendsMapper;
import com.mtcode.mblogapi.service.FriendsService;
import com.mtcode.mblogapi.util.Auth;
import com.mtcode.mblogapi.util.Func;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author TangMingZhang
 * @date 2022/3/30
 */
@Service
public class FriendsServiceImpl extends ServiceImpl<FriendsMapper, Friends> implements FriendsService {

    @Override
    public void updateOrSaveFriends(Friends friends) {
        if (friends != null) {
            if (Func.isEmptyAsString(
                    friends.getNickname(),
                    friends.getNickname(),
                    friends.getNickname(),
                    friends.getWebsite())) {
                throw new ParameterException("参数错误");
            }

            Date date = new Date();
            if (friends.getId() == null) {
                friends.setCreateTime(date).setCreateUser(Auth.getUserId()).setIsPublished(true);
                save(friends);
            } else {
                friends.setUpdateTime(date);
                updateById(friends);
            }
        } else {
            throw new ParameterException("参数错误");
        }
    }
}
