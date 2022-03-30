package com.mtcode.mblogapi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mtcode.mblogapi.entity.FriendsInfo;
import com.mtcode.mblogapi.exception.ParameterException;
import com.mtcode.mblogapi.mapper.FriendsInfoMapper;
import com.mtcode.mblogapi.service.FriendsInfoService;
import com.mtcode.mblogapi.util.Auth;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author TangMingZhang
 * @date 2022/3/30
 */
@Service
public class FriendsInfoServiceImpl extends ServiceImpl<FriendsInfoMapper, FriendsInfo> implements FriendsInfoService {

    @Override
    public void updateOrSaveFriendsInfo(FriendsInfo friendsInfo) {
        if (friendsInfo != null) {
            Date date = new Date();
            if (friendsInfo.getId() == null) {
                friendsInfo.setCreateTime(date).setCreateUser(Auth.getUserId());
                save(friendsInfo);
            } else {
                friendsInfo.setUpdateTime(date);
                updateById(friendsInfo);
            }
        } else {
            throw new ParameterException("参数错误");
        }
    }
}
