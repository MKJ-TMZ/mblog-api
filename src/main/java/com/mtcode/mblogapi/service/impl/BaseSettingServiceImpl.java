package com.mtcode.mblogapi.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mtcode.mblogapi.constant.RedisConstant;
import com.mtcode.mblogapi.entity.BaseSetting;
import com.mtcode.mblogapi.exception.ParameterException;
import com.mtcode.mblogapi.mapper.BaseSettingMapper;
import com.mtcode.mblogapi.service.BaseSettingService;
import com.mtcode.mblogapi.util.Auth;
import com.mtcode.mblogapi.util.CacheUtils;
import com.mtcode.mblogapi.vo.Result;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author TangMingZhang
 * @date 2022/4/4
 */
@Service
public class BaseSettingServiceImpl extends ServiceImpl<BaseSettingMapper, BaseSetting> implements BaseSettingService {

    @Override
    public boolean saveOrUpdateBaseSetting(BaseSetting baseSetting) {
        if (baseSetting != null) {
            boolean result;
            if (baseSetting.getId() == null) {
                baseSetting.setCreateTime(new Date()).setCreateUser(Auth.getUserId());
                result = save(baseSetting);
            } else {
                baseSetting.setUpdateTime(new Date());
                result = updateById(baseSetting);
            }
            CacheUtils.delete(RedisConstant.SETTING + "base");
            return result;
        } else {
            throw new ParameterException("参数错误");
        }
    }

    @Override
    public BaseSetting getBase() {
        BaseSetting baseSetting = CacheUtils.getValue(RedisConstant.SETTING + "base", BaseSetting.class);
        if (baseSetting == null) {
            BaseSetting baseSettingNow = getOne(Wrappers.lambdaQuery());
            if (baseSettingNow == null) {
                return new BaseSetting();
            }
            CacheUtils.setValue(RedisConstant.SETTING + "base", baseSettingNow);
            return baseSettingNow;
        } else {
            return baseSetting;
        }
    }
}
