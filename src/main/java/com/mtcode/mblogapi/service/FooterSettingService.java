package com.mtcode.mblogapi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mtcode.mblogapi.entity.FooterSetting;

import java.util.List;

/**
 * @author TangMingZhang
 * @date 2022/4/5
 */
public interface FooterSettingService extends IService<FooterSetting> {

    /**
     * 保存或更新页脚设置
     *
     * @param footerSetting FooterSetting
     * @return 保存或更新结果
     */
    boolean saveOrUpdateFooterSetting(FooterSetting footerSetting);

    /**
     * 获取页脚设置
     *
     * @return FooterSetting实体类list
     */
    List<FooterSetting> getFooter();
}
