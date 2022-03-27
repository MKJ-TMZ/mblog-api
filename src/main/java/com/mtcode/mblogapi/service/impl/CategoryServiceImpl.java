package com.mtcode.mblogapi.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mtcode.mblogapi.constant.RedisConstant;
import com.mtcode.mblogapi.entity.Category;
import com.mtcode.mblogapi.entity.Tag;
import com.mtcode.mblogapi.exception.ParameterException;
import com.mtcode.mblogapi.mapper.CategoryMapper;
import com.mtcode.mblogapi.service.CategoryService;
import com.mtcode.mblogapi.util.CacheUtils;
import com.mtcode.mblogapi.util.Func;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author TangMingZhang
 * @date 2022/3/22
 */
@Service
@AllArgsConstructor
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    private final CacheUtils cacheUtils;

    @Override
    public String getCategoryName(Long categoryId) {
        Category category = cacheUtils.getValue(RedisConstant.CATEGORY + categoryId, Category.class);
        if (category == null) {
            Category CategoryData = getOne(Wrappers.lambdaQuery(Category.class).eq(Category::getId, categoryId));
            if (CategoryData != null) {
                cacheUtils.setValue(RedisConstant.CATEGORY + CategoryData.getId(), CategoryData);
                return CategoryData.getName();
            } else {
                return "";
            }
        } else {
            return category.getName();
        }
    }

    @Override
    public void updateOrSaveCategory(Category category) {
        if (category != null) {
            if (Func.isEmptyAsString(category.getName())) {
                throw new ParameterException("参数错误");
            }

            if (category.getId() == null) {
                save(category);
            } else {
                updateById(category);
            }
        } else {
            throw new ParameterException("参数错误");
        }
    }
}
