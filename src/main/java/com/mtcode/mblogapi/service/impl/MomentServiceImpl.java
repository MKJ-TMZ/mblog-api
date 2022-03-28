package com.mtcode.mblogapi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mtcode.mblogapi.entity.Moment;
import com.mtcode.mblogapi.exception.NullException;
import com.mtcode.mblogapi.exception.ParameterException;
import com.mtcode.mblogapi.mapper.MomentMapper;
import com.mtcode.mblogapi.service.MomentService;
import com.mtcode.mblogapi.util.Func;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author TangMingZhang
 * @date 2022/3/28
 */
@Service
public class MomentServiceImpl extends ServiceImpl<MomentMapper, Moment> implements MomentService {
    @Override
    public void updateOrSaveMoment(Moment moment) {
        if (moment != null) {
            if (Func.isEmptyAsString(moment.getContent())) {
                throw new ParameterException("参数错误");
            }

            Date date = new Date();
            if (moment.getId() == null) {
                moment.setCreateTime(date);
                save(moment);
            } else {
                moment.setCreateTime(date);
                updateById(moment);
            }
        } else {
            throw new ParameterException("参数错误");
        }
    }

    @Override
    public void update(Moment moment) {
        if (moment != null && moment.getId() != null) {
            updateById(moment);
        } else {

            throw new ParameterException("参数错误");

        }
    }

    @Override
    public Moment detail(Long id) {
        if (id == null) {
            throw new ParameterException("参数错误");
        }

        Moment moment = getById(id);
        if (moment == null) {
            throw new NullException("该动态不存在");
        }

        return moment;
    }
}
