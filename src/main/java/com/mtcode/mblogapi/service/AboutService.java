package com.mtcode.mblogapi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mtcode.mblogapi.entity.About;

/**
 * @author TangMingZhang
 * @date 2022/3/31
 */
public interface AboutService extends IService<About> {

    /**
     * 更新或保存
     *
     * @param about about
     */
    void updateOrSaveAbout(About about);
}
