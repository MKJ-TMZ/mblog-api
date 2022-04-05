package com.mtcode.mblogapi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mtcode.mblogapi.entity.FooterSetting;

/**
 * @author TangMingZhang
 * @date 2022/4/5
 */
public interface FooterSettingService extends IService<FooterSetting> {

    boolean saveOrUpdateFooterSetting(FooterSetting footerSetting);
}
