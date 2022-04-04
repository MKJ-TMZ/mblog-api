package com.mtcode.mblogapi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mtcode.mblogapi.entity.ProfileSetting;

/**
 * @author TangMingZhang
 * @date 2022/4/4
 */
public interface ProfileSettingService extends IService<ProfileSetting> {

    boolean saveOrUpdateProfileSetting(ProfileSetting profileSetting);
}
