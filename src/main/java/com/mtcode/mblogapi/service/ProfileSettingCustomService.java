package com.mtcode.mblogapi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mtcode.mblogapi.entity.ProfileSettingCustom;

/**
 * @author TangMingZhang
 * @date 2022/4/4
 */
public interface ProfileSettingCustomService extends IService<ProfileSettingCustom> {

    boolean saveOrUpdateProfileSettingCustom(ProfileSettingCustom profileSettingCustom);
}
