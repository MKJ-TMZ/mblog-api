package com.mtcode.mblogapi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mtcode.mblogapi.entity.ProfileSetting;
import com.mtcode.mblogapi.exception.ParameterException;
import com.mtcode.mblogapi.mapper.ProfileSettingMapper;
import com.mtcode.mblogapi.service.ProfileSettingService;
import com.mtcode.mblogapi.util.Auth;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author TangMingZhang
 * @date 2022/4/4
 */
@Service
public class ProfileSettingServiceImpl extends ServiceImpl<ProfileSettingMapper, ProfileSetting> implements ProfileSettingService {

    @Override
    public boolean saveOrUpdateProfileSetting(ProfileSetting profileSetting) {
        if (profileSetting != null) {
            boolean result;
            if (profileSetting.getId() == null) {
                profileSetting.setCreateTime(new Date()).setCreateUser(Auth.getUserId());
                result = save(profileSetting);
            } else {
                profileSetting.setUpdateTime(new Date());
                result = updateById(profileSetting);
            }
            return result;
        } else {
            throw new ParameterException("参数错误");
        }
    }
}
