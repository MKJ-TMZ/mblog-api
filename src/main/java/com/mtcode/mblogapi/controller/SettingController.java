package com.mtcode.mblogapi.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.mtcode.mblogapi.constant.RedisConstant;
import com.mtcode.mblogapi.converter.ProfileSettingConverter;
import com.mtcode.mblogapi.entity.*;
import com.mtcode.mblogapi.service.*;
import com.mtcode.mblogapi.util.CacheUtils;
import com.mtcode.mblogapi.vo.ProfileSettingVO;
import com.mtcode.mblogapi.vo.Result;
import lombok.AllArgsConstructor;
import org.springframework.data.util.CastUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private final FooterSettingService footerSettingService;
    private final BlogService blogService;

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

    @GetMapping("/footer")
    public Result getFooter() {
        Object footerSettingListCache = CacheUtils.getValue(RedisConstant.SETTING + "footer:list", List.class);
        List<FooterSetting> footerSettingList;
        if (footerSettingListCache == null) {
            footerSettingList = footerSettingService.list();
            if (footerSettingList == null) {
                footerSettingList = new ArrayList<>();
            }
            CacheUtils.setValue(RedisConstant.SETTING + "footer:list", footerSettingList);
        } else {
            footerSettingList = CastUtils.cast(footerSettingListCache);
        }

        List<Blog> blogList = blogService.list(Wrappers.lambdaQuery(Blog.class)
                .eq(Blog::getIsPublished, true)
                .orderByDesc(Blog::getCreateTime)
                .last("limit 5"));
        if (blogList == null) {
            blogList = new ArrayList<>();
        }

        Map<String, Object> result = new HashMap<>();
        result.put("badgeList", footerSettingList);
        result.put("newBlogList", blogList);

        return Result.ok(result);
    }
}
