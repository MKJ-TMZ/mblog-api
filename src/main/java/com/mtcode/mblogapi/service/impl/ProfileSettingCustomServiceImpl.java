package com.mtcode.mblogapi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mtcode.mblogapi.entity.ProfileSettingCustom;
import com.mtcode.mblogapi.exception.ParameterException;
import com.mtcode.mblogapi.mapper.ProfileSettingCustomMapper;
import com.mtcode.mblogapi.service.ProfileSettingCustomService;
import com.mtcode.mblogapi.util.Auth;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author TangMingZhang
 * @date 2022/4/4
 */
@Service
public class ProfileSettingCustomServiceImpl extends ServiceImpl<ProfileSettingCustomMapper, ProfileSettingCustom>
        implements ProfileSettingCustomService {

    @Override
    public boolean saveOrUpdateProfileSettingCustom(ProfileSettingCustom profileSettingCustom) {
        if (profileSettingCustom != null) {
            boolean result;
            if (profileSettingCustom.getId() == null) {
                profileSettingCustom.setCreateTime(new Date()).setCreateUser(Auth.getUserId());
                result = save(profileSettingCustom);
            } else {
                profileSettingCustom.setUpdateTime(new Date());
                result = updateById(profileSettingCustom);
            }
            return result;
        } else {
            throw new ParameterException("参数错误");
        }
    }
}
