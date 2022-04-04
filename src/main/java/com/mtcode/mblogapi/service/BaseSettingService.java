package com.mtcode.mblogapi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mtcode.mblogapi.entity.BaseSetting;

/**
 * @author TangMingZhang
 * @date 2022/4/4
 */
public interface BaseSettingService extends IService<BaseSetting> {

    boolean saveOrUpdateBaseSetting(BaseSetting baseSetting);
}
