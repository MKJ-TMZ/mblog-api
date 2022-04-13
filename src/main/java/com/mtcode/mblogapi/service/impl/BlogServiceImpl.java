package com.mtcode.mblogapi.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mtcode.mblogapi.constant.RedisConstant;
import com.mtcode.mblogapi.converter.BlogConverter;
import com.mtcode.mblogapi.entity.Blog;
import com.mtcode.mblogapi.entity.BlogTag;
import com.mtcode.mblogapi.entity.Category;
import com.mtcode.mblogapi.entity.Tag;
import com.mtcode.mblogapi.exception.NullException;
import com.mtcode.mblogapi.exception.ParameterException;
import com.mtcode.mblogapi.mapper.BlogMapper;
import com.mtcode.mblogapi.service.BlogService;
import com.mtcode.mblogapi.service.BlogTagService;
import com.mtcode.mblogapi.service.CategoryService;
import com.mtcode.mblogapi.service.TagService;
import com.mtcode.mblogapi.util.Auth;
import com.mtcode.mblogapi.util.CacheUtils;
import com.mtcode.mblogapi.util.Func;
import com.mtcode.mblogapi.util.MarkdownUtils;
import com.mtcode.mblogapi.vo.BlogVO;
import com.mtcode.mblogapi.vo.Result;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

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
                Category category = categoryService.saveLackCategory(blogVO.getCategoryName());
                blogVO.setCategoryId(category.getId());
            }

            Date date = new Date();
            if (blogVO.getId() == null) {
                blogVO.setCreateTime(date).setCreateUser(Auth.getUserId());
                save(blogVO);
            } else {
                blogVO.setUpdateTime(date);
                updateById(blogVO);
            }
            CacheUtils.delete(RedisConstant.BLOG + blogVO.getId());

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
            CacheUtils.delete(RedisConstant.BLOG + blog.getId());
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

    @Override
    public IPage<BlogVO> homePage(Page<BlogVO> query, BlogVO blogVO) {
        List<BlogVO> blogVOList = baseMapper.selectHomePage(query, blogVO);
        if (blogVOList != null && blogVOList.size() > 0) {
            for (BlogVO blog : blogVOList) {
                blog.setDescription(MarkdownUtils.markdownToHtml(blog.getDescription()));
                List<Tag> tagList = tagService.getTagsByBlogId(blog.getId());
                blog.setTagList(tagList);
            }
        }
        return query.setRecords(blogVOList);
    }

    @Override
    public BlogVO homeDetail(Long id) {
        BlogVO blogVOCache = CacheUtils.getValue(RedisConstant.BLOG + id, BlogVO.class);
        if (blogVOCache == null) {
            Blog blog = getById(id);
            if (blog == null) {
                return null;
            } else {
                BlogVO blogVO = BlogConverter.INSTANCE.blogToBlogVO(blog);
                blogVO.setContent(MarkdownUtils.markdownToHtml(blogVO.getContent()));
                blogVO.setDescription(MarkdownUtils.markdownToHtml(blogVO.getDescription()));
                Category category = categoryService.getById(blogVO.getCategoryId());
                if (category != null) {
                    blogVO.setCategoryName(category.getName());
                }
                List<Tag> tagList = tagService.getTagsByBlogId(id);
                blogVO.setTagList(tagList);
                CacheUtils.setValue(RedisConstant.BLOG + id, blogVO);

                return blogVO;
            }
        } else {
            return blogVOCache;
        }
    }

    @Override
    public List<Map<String, Object>> archives() {
        List<BlogVO> blogList = baseMapper.selectHomePage(null, new BlogVO());
        Calendar calender = Calendar.getInstance();
        // 通过年月分组
        Map<Integer, List<Blog>> groupByMonth = blogList.stream().collect(Collectors.groupingBy(blog -> {
            calender.setTime(blog.getCreateTime());
            int year = calender.get(Calendar.YEAR);
            int month = calender.get(Calendar.MONTH) + 1;
            if (month < 10) {
                return Integer.parseInt(year + "0" + month);
            } else {
                return Integer.parseInt(year + "" + month);
            }
        }));
        List<Map<String, Object>> result = new ArrayList<>();
        // 通过年月排序并存入list
        for (Map.Entry<Integer, List<Blog>> entry : groupByMonth.entrySet()) {
            HashMap<String, Object> blogGroup = new HashMap<>();
            blogGroup.put("yearMonth", entry.getKey());
            List<Blog> blogs = entry.getValue();
            blogs = blogs.stream()
                    .sorted(Comparator.comparing(Blog::getCreateTime).reversed()).collect(Collectors.toList());
            blogGroup.put("group", blogs);
            result.add(blogGroup);
        }
        result.sort(Comparator.comparing(map -> (Integer) map.get("yearMonth")));
        Collections.reverse(result);
        return result;
    }

    @Override
    public List<Blog> search(String query) {
        List<Blog> blogs = baseMapper.search(query);
        blogs.forEach(blog -> blog.setDescription(MarkdownUtils.markdownToHtml(blog.getDescription())));
        return blogs;
    }
}
