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
    private final FooterSettingService footerSettingService;
    private final BlogService blogService;

    @GetMapping("/base")
    public Result getBase() {
        return Result.ok(baseSettingService.getBase());
    }

    @GetMapping("/profile")
    public Result getProfile() {
        return Result.ok(profileSettingService.getProfile());
    }

    @GetMapping("/footer")
    public Result getFooter() {
        List<FooterSetting> footerSettingList = footerSettingService.getFooter();
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
