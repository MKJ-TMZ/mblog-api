package com.mtcode.mblogapi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mtcode.mblogapi.entity.BaseSetting;
import com.mtcode.mblogapi.exception.ParameterException;
import com.mtcode.mblogapi.mapper.BaseSettingMapper;
import com.mtcode.mblogapi.service.BaseSettingService;
import com.mtcode.mblogapi.util.Auth;
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
            return result;
        } else {
            throw new ParameterException("参数错误");
        }
    }
}
