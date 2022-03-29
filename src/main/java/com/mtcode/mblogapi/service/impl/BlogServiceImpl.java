package com.mtcode.mblogapi.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mtcode.mblogapi.converter.BlogConverter;
import com.mtcode.mblogapi.entity.Blog;
import com.mtcode.mblogapi.entity.BlogTag;
import com.mtcode.mblogapi.entity.Category;
import com.mtcode.mblogapi.exception.NullException;
import com.mtcode.mblogapi.exception.ParameterException;
import com.mtcode.mblogapi.mapper.BlogMapper;
import com.mtcode.mblogapi.service.BlogService;
import com.mtcode.mblogapi.service.BlogTagService;
import com.mtcode.mblogapi.service.CategoryService;
import com.mtcode.mblogapi.service.TagService;
import com.mtcode.mblogapi.util.Auth;
import com.mtcode.mblogapi.util.Func;
import com.mtcode.mblogapi.vo.BlogVO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author TangMingZhang
 * @date 2022/3/20
 */
@Service
@AllArgsConstructor
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {

    private final CategoryService categoryService;
    private final BlogTagService blogTagService;
    private final TagService tagService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateOrSaveBlog(BlogVO blogVO) {
        if (blogVO != null) {
            if (!blogVO.getIsDraft() && Func.isEmptyAsString(blogVO.getTitle(), blogVO.getContent(), blogVO.getDescription())) {
                throw new ParameterException("参数错误");
            }

            // 保存分类id
            if (Func.isNotEmptyAsString(blogVO.getCategoryName())) {
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
            }

            Date date = new Date();
            if (blogVO.getId() == null) {
                blogVO.setCreateTime(date);
                blogVO.setCreateUser(Auth.getUserId());
                save(blogVO);
            } else {
                blogVO.setUpdateTime(date);
                updateById(blogVO);
            }

            // 保存博客标签关联信息
            blogTagService.saveBlogTagList(blogVO.getId(), blogVO.getTagNameSet());
        } else {
            throw new ParameterException("参数错误");
        }
    }

    @Override
    public IPage<BlogVO> pageVO(Page<BlogVO> query, BlogVO blogVO) {
        List<BlogVO> blogVOList = baseMapper.selectPageVO(query, blogVO);
        for (BlogVO blog : blogVOList) {
            if (blog != null) {
                blog.setCategoryName(categoryService.getCategoryName(blog.getCategoryId()));
            }
        }
        return query.setRecords(blogVOList);
    }

    @Override
    public void update(Blog blog) {
        if (blog != null && blog.getId() != null) {
            updateById(blog);
        } else {
            throw new ParameterException("参数错误");
        }
    }

    @Override
    public BlogVO detail(Long id) {
        if (id == null) {
            throw new ParameterException("参数错误");
        }

        Blog blog = getById(id);
        if (blog == null) {
            throw new NullException("该博客不存在");
        }
        BlogVO blogVO = BlogConverter.INSTANCE.blogToBlogVO(blog);
        blogVO.setCategoryName(categoryService.getCategoryName(blogVO.getCategoryId()));

        List<BlogTag> blogTagList = blogTagService.list(Wrappers.lambdaQuery(BlogTag.class).eq(BlogTag::getBlogId, blogVO.getId()));
        if (blogTagList != null) {
            Set<String> tagNameSet = new HashSet<>();
            for (BlogTag blogTag : blogTagList) {
                if (blogTag != null && blogTag.getTagId() != null) {
                    tagNameSet.add(tagService.getTagName(blogTag.getTagId()));
                }
            }
            blogVO.setTagNameSet(tagNameSet);
        }

        return blogVO;
    }
}
