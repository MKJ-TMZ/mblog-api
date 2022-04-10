package com.mtcode.mblogapi.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mtcode.mblogapi.constant.RedisConstant;
import com.mtcode.mblogapi.converter.ProfileSettingConverter;
import com.mtcode.mblogapi.entity.ProfileSetting;
import com.mtcode.mblogapi.entity.ProfileSettingCustom;
import com.mtcode.mblogapi.exception.ParameterException;
import com.mtcode.mblogapi.mapper.ProfileSettingMapper;
import com.mtcode.mblogapi.service.ProfileSettingCustomService;
import com.mtcode.mblogapi.service.ProfileSettingService;
import com.mtcode.mblogapi.util.Auth;
import com.mtcode.mblogapi.util.CacheUtils;
import com.mtcode.mblogapi.vo.ProfileSettingVO;
import com.mtcode.mblogapi.vo.Result;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author TangMingZhang
 * @date 2022/4/4
 */
@Service
@AllArgsConstructor
public class ProfileSettingServiceImpl extends ServiceImpl<ProfileSettingMapper, ProfileSetting> implements ProfileSettingService {

    private final ProfileSettingCustomService profileSettingCustomService;

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

    @Override
    public ProfileSettingVO getProfile() {
        ProfileSetting profileSetting = getOne(Wrappers.lambdaQuery());
        ProfileSettingVO profileSettingVO = ProfileSettingConverter.INSTANCE.profileSettingToProfileSettingVO(profileSetting);
        if (profileSettingVO == null) {
            return new ProfileSettingVO();
        }
        List<ProfileSettingCustom> customList = profileSettingCustomService.list();
        if (customList == null) {
            customList = new ArrayList<>();
        }
        profileSettingVO.setCustomList(customList);
        return profileSettingVO;
    }
}
