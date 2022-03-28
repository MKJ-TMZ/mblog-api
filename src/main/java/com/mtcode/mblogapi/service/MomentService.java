package com.mtcode.mblogapi.service;

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
}
