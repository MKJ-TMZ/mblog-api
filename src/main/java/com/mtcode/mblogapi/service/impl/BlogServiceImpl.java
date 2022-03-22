package com.mtcode.mblogapi.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mtcode.mblogapi.entity.Blog;
import com.mtcode.mblogapi.entity.Category;
import com.mtcode.mblogapi.exception.ParameterException;
import com.mtcode.mblogapi.mapper.BlogMapper;
import com.mtcode.mblogapi.service.BlogService;
import com.mtcode.mblogapi.service.BlogTagService;
import com.mtcode.mblogapi.service.CategoryService;
import com.mtcode.mblogapi.util.Func;
import com.mtcode.mblogapi.vo.BlogVO;
import com.mtcode.mblogapi.vo.Result;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author TangMingZhang
 * @date 2022/3/20
 */
@Service
@AllArgsConstructor
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {

    private final CategoryService categoryService;
    private final BlogTagService blogTagService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result updateOrSaveBlog(BlogVO blogVO) {
        if (blogVO != null) {
            if (!blogVO.getIsDraft() && Func.isEmptyAsString(blogVO.getTitle(), blogVO.getContent(), blogVO.getDescription())) {
                throw new ParameterException("参数错误");
            }

            // 保存分类id
            Category category = categoryService
                    .getOne(Wrappers.lambdaQuery(Category.class).eq(Category::getName, blogVO.getCategoryName()));
            if (category == null) {
                Category newCategory = new Category();
                newCategory.setName(blogVO.getCategoryName());
                categoryService.save(newCategory);
                blogVO.setCategoryId(newCategory.getId());
            } else {
                blogVO.setCategoryId(category.getId());
            }

            Date date = new Date();
            if (blogVO.getId() == null) {
                blogVO.setCreateTime(date);
                save(blogVO);
            } else {
                blogVO.setUpdateTime(date);
                updateById(blogVO);
            }

            // 保存博客标签关联信息
            blogTagService.saveBlogTagList(blogVO.getId(), blogVO.getTagNameSet());

            return Result.ok("成功");
        } else {
            return Result.error("参数错误");
        }
    }
}
