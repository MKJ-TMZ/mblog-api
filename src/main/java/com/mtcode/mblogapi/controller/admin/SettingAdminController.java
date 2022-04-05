package com.mtcode.mblogapi.controller.admin;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mtcode.mblogapi.entity.BaseSetting;
import com.mtcode.mblogapi.entity.FooterSetting;
import com.mtcode.mblogapi.entity.ProfileSetting;
import com.mtcode.mblogapi.entity.ProfileSettingCustom;
import com.mtcode.mblogapi.service.BaseSettingService;
import com.mtcode.mblogapi.service.FooterSettingService;
import com.mtcode.mblogapi.service.ProfileSettingCustomService;
import com.mtcode.mblogapi.service.ProfileSettingService;
import com.mtcode.mblogapi.vo.Result;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author TangMingZhang
 * @date 2022/4/4
 */
@RestController
@RequestMapping("/admin/setting")
@AllArgsConstructor
public class SettingAdminController {

    private final BaseSettingService baseSettingService;
    private final ProfileSettingService profileSettingService;
    private final ProfileSettingCustomService profileSettingCustomService;
    private final FooterSettingService footerSettingService;

    @PostMapping("/base")
    public Result baseSave(@RequestBody BaseSetting baseSetting) {
        return new Result(baseSettingService.saveOrUpdateBaseSetting(baseSetting));
    }

    @GetMapping("/base")
    public Result baseDetail() {
        BaseSetting baseSetting = baseSettingService.getOne(Wrappers.lambdaQuery());
        if (baseSetting == null) {
            baseSetting = new BaseSetting();
        }
        return Result.ok(baseSetting);
    }

    @PostMapping("/profile")
    public Result profileSave(@RequestBody ProfileSetting profileSetting) {
        return new Result(profileSettingService.saveOrUpdateProfileSetting(profileSetting));
    }

    @GetMapping("/profile")
    public Result profileDetail() {
        ProfileSetting profileSetting = profileSettingService.getOne(Wrappers.lambdaQuery());
        if (profileSetting == null) {
            profileSetting = new ProfileSetting();
        }
        return Result.ok(profileSetting);
    }

    @PostMapping("/profile/custom")
    public Result profileCustomSave(@RequestBody ProfileSettingCustom profileSettingCustom) {
        return new Result(profileSettingCustomService.saveOrUpdateProfileSettingCustom(profileSettingCustom));
    }

    @GetMapping("/profile/custom/page")
    public Result profileCustomPage(Page<ProfileSettingCustom> query) {
        return Result.ok(profileSettingCustomService.page(query));
    }

    @DeleteMapping("/profile/custom/{id}")
    public Result profileCustomDelete(@PathVariable Long id) {
        return new Result(profileSettingCustomService.removeById(id));
    }

    @PostMapping("/footer")
    public Result footerSave(@RequestBody FooterSetting footerSetting) {
        return new Result(footerSettingService.saveOrUpdateFooterSetting(footerSetting));
    }

    @DeleteMapping("/footer/{id}")
    public Result footerSave(@PathVariable Long id) {
        return new Result(footerSettingService.removeById(id));
    }

    @GetMapping("/footer/page")
    public Result FooterPage(Page<FooterSetting> query) {
        return Result.ok(footerSettingService.page(query));
    }

}
