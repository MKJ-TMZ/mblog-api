package com.mtcode.mblogapi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mtcode.mblogapi.entity.Category;

/**
 * @author TangMingZhang
 * @date 2022/3/22
 */
public interface CategoryService extends IService<Category> {

    /**
     * 获取分类名称
     *
     * @param categoryId 分类id
     * @return 分类名称
     */
    String getCategoryName(Long categoryId);

    /**
     * 更新或保存
     *
     * @param category category实体类
     */
    void updateOrSaveCategory(Category category);
}
