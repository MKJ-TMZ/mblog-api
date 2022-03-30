package com.mtcode.mblogapi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mtcode.mblogapi.entity.Friends;

/**
 * @author TangMingZhang
 * @date 2022/3/30
 */
public interface FriendsService extends IService<Friends> {

    /**
     * 更新或保存
     *
     * @param friends friends
     */
    void updateOrSaveFriends(Friends friends);
}
