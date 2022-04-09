package com.mtcode.mblogapi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mtcode.mblogapi.constant.RedisConstant;
import com.mtcode.mblogapi.entity.FooterSetting;
import com.mtcode.mblogapi.exception.ParameterException;
import com.mtcode.mblogapi.mapper.FooterSettingMapper;
import com.mtcode.mblogapi.service.FooterSettingService;
import com.mtcode.mblogapi.util.Auth;
import com.mtcode.mblogapi.util.CacheUtils;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author TangMingZhang
 * @date 2022/4/5
 */
@Service
public class FooterSettingServiceImpl extends ServiceImpl<FooterSettingMapper, FooterSetting> implements FooterSettingService {

    @Override
    public boolean saveOrUpdateFooterSetting(FooterSetting footerSetting) {
        if (footerSetting == null) {
            throw new ParameterException("参数错误");
        } else {
            boolean result;
            if (footerSetting.getId() == null) {
                footerSetting.setCreateTime(new Date())
                        .setCreateUser(Auth.getUserId());
                result = save(footerSetting);
                CacheUtils.delete(RedisConstant.SETTING + "footer:list");
            } else {
                footerSetting.setUpdateTime(new Date());
                result = updateById(footerSetting);
                CacheUtils.delete(RedisConstant.SETTING + "footer:list");
            }

            return result;
        }
    }
}
