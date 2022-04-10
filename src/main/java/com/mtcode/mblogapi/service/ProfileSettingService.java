package com.mtcode.mblogapi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mtcode.mblogapi.entity.ProfileSetting;
import com.mtcode.mblogapi.vo.ProfileSettingVO;

/**
 * @author TangMingZhang
 * @date 2022/4/4
 */
public interface ProfileSettingService extends IService<ProfileSetting> {

    /**
     * 保存或更新个人档案
     *
     * @param profileSetting profileSetting实体类
     * @return 保存或更新结果
     */
    boolean saveOrUpdateProfileSetting(ProfileSetting profileSetting);

    /**
     * 获取个人档案
     *
     * @return ProfileSettingVO实体类
     */
    ProfileSettingVO getProfile();
}
