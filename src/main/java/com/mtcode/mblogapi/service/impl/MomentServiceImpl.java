package com.mtcode.mblogapi.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mtcode.mblogapi.entity.Moment;
import com.mtcode.mblogapi.exception.NullException;
import com.mtcode.mblogapi.exception.ParameterException;
import com.mtcode.mblogapi.mapper.MomentMapper;
import com.mtcode.mblogapi.service.MomentService;
import com.mtcode.mblogapi.util.Auth;
import com.mtcode.mblogapi.util.Func;
import com.mtcode.mblogapi.util.MarkdownUtils;
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
                moment.setCreateTime(date).setCreateUser(Auth.getUserId());
                save(moment);
            } else {
                moment.setUpdateTime(date);
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

    @Override
    public IPage<Moment> homePage(Page<Moment> query) {
        Page<Moment> page = page(query, Wrappers.lambdaQuery(Moment.class)
                .eq(Moment::getIsPublished, true)
                .orderByDesc(Moment::getCreateTime));
        if (page.getRecords() != null && page.getRecords().size() > 0) {
            for (Moment moment : page.getRecords()) {
                moment.setContent(MarkdownUtils.markdownToHtml(moment.getContent()));
            }
        }
        return page;
    }
}
