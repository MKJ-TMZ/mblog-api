package com.mtcode.mblogapi.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mtcode.mblogapi.entity.Moment;

/**
 * @author TangMingZhang
 * @date 2022/3/28
 */
public interface MomentService extends IService<Moment> {

    /**
     * 更新或保存
     *
     * @param moment moment
     */
    void updateOrSaveMoment(Moment moment);

    /**
     * 更新
     *
     * @param moment moment实体类
     */
    void update(Moment moment);

    /**
     * 动态详情
     *
     * @param id 动态id
     * @return 实体类
     */
    Moment detail(Long id);

    /**
     * 动态主页查询
     *
     * @param query 查询条件
     * @return 分页实体
     */
    IPage<Moment> homePage(Page<Moment> query);
}
