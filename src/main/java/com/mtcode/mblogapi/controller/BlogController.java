package com.mtcode.mblogapi.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.mtcode.mblogapi.converter.BlogConverter;
import com.mtcode.mblogapi.entity.Blog;
import com.mtcode.mblogapi.entity.Category;
import com.mtcode.mblogapi.entity.Tag;
import com.mtcode.mblogapi.service.BlogService;
import com.mtcode.mblogapi.service.CategoryService;
import com.mtcode.mblogapi.service.TagService;
import com.mtcode.mblogapi.util.MarkdownUtils;
import com.mtcode.mblogapi.vo.BlogVO;
import com.mtcode.mblogapi.vo.Result;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author TangMingZhang
 * @date 2022/4/9
 */
@RestController
@RequestMapping("/blog")
@AllArgsConstructor
public class BlogController {

    private final BlogService blogService;
    private final CategoryService categoryService;
    private final TagService tagService;

    @GetMapping("/recommend")
    public Result recommend() {
        List<Blog> blogList = blogService.list(Wrappers.lambdaQuery(Blog.class)
                        .eq(Blog::getIsPublished, true)
                        .eq(Blog::getIsRecommend, true)
                        .orderByDesc(Blog::getCreateTime)
                        .last("limit 5"));

        return Result.ok(blogList);
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable("id") Long id) {
        Blog blog = blogService.getById(id);
        if (blog == null) {
            return Result.fail("该博客不存在");
        } else {
            BlogVO blogVO = BlogConverter.INSTANCE.blogToBlogVO(blog);
            blogVO.setContent(MarkdownUtils.markdownToHtml(blogVO.getContent()));
            blogVO.setTitle(MarkdownUtils.markdownToHtml(blogVO.getTitle()));
            Category category = categoryService.getById(blogVO.getCategoryId());
            if (category != null) {
                blogVO.setCategoryName(category.getName());
            }
            List<Tag> tagList = tagService.getTagsByBlogId(id);
            blogVO.setTagList(tagList);

            return Result.ok(blogVO);
        }
    }
}
