package com.mtcode.mblogapi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mtcode.mblogapi.entity.FriendsInfo;

/**
 * @author TangMingZhang
 * @date 2022/3/30
 */
public interface FriendsInfoService extends IService<FriendsInfo> {

    /**
     * 更新或保存
     *
     * @param friendsInfo friendsInfo
     */
    void updateOrSaveFriendsInfo(FriendsInfo friendsInfo);
}
