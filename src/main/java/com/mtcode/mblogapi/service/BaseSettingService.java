package com.mtcode.mblogapi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mtcode.mblogapi.entity.BaseSetting;

/**
 * @author TangMingZhang
 * @date 2022/4/4
 */
public interface BaseSettingService extends IService<BaseSetting> {

    /**
     * 保存或更新基础设置
     *
     * @param baseSetting baseSetting实体类
     * @return 保存结果
     */
    boolean saveOrUpdateBaseSetting(BaseSetting baseSetting);

    /**
     * 获取基础设置
     * @return
     */
    BaseSetting getBase();
}
