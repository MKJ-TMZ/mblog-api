package com.mtcode.mblogapi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mtcode.mblogapi.entity.About;
import com.mtcode.mblogapi.exception.ParameterException;
import com.mtcode.mblogapi.mapper.AboutMapper;
import com.mtcode.mblogapi.service.AboutService;
import com.mtcode.mblogapi.util.Auth;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author TangMingZhang
 * @date 2022/3/31
 */
@Service
public class AboutServiceImpl extends ServiceImpl<AboutMapper, About> implements AboutService {

    @Override
    public void updateOrSaveAbout(About about) {
        if (about != null) {
            if (about.getId() == null) {
                about.setCreateTime(new Date()).setCreateUser(Auth.getUserId());
                save(about);
            } else {
                about.setUpdateTime(new Date());
                updateById(about);
            }
        } else {
            throw new ParameterException("参数错误");
        }
    }
}
