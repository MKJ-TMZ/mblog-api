package com.mtcode.mblogapi.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.mtcode.mblogapi.constant.RedisConstant;
import com.mtcode.mblogapi.converter.ProfileSettingConverter;
import com.mtcode.mblogapi.entity.BaseSetting;
import com.mtcode.mblogapi.entity.ProfileSetting;
import com.mtcode.mblogapi.entity.ProfileSettingCustom;
import com.mtcode.mblogapi.service.BaseSettingService;
import com.mtcode.mblogapi.service.ProfileSettingCustomService;
import com.mtcode.mblogapi.service.ProfileSettingService;
import com.mtcode.mblogapi.util.CacheUtils;
import com.mtcode.mblogapi.vo.ProfileSettingVO;
import com.mtcode.mblogapi.vo.Result;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author TangMingZhang
 * @date 2022/4/5
 */
@RestController
@RequestMapping("/setting")
@AllArgsConstructor
public class SettingController {

    private final BaseSettingService baseSettingService;
    private final ProfileSettingService profileSettingService;
    private final ProfileSettingCustomService profileSettingCustomService;

    @GetMapping("/base")
    public Result getBase() {
        BaseSetting baseSetting = CacheUtils.getValue(RedisConstant.SETTING + "base", BaseSetting.class);
        if (baseSetting == null) {
            BaseSetting baseSettingNow = baseSettingService.getOne(Wrappers.lambdaQuery());
            CacheUtils.setValue(RedisConstant.SETTING + "base", baseSettingNow);
            return Result.ok(baseSettingNow);
        } else {
            return Result.ok(baseSetting);
        }
    }

    @GetMapping("/profile")
    public Result getProfile() {
        ProfileSetting profileSetting = profileSettingService.getOne(Wrappers.lambdaQuery());
        ProfileSettingVO profileSettingVO = ProfileSettingConverter.INSTANCE.profileSettingToProfileSettingVO(profileSetting);
        List<ProfileSettingCustom> customList = profileSettingCustomService.list();
        if (customList == null) {
            customList = new ArrayList<>();
        }
        profileSettingVO.setCustomList(customList);
        return Result.ok(profileSettingVO);
    }
}
