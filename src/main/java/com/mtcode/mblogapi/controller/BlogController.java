package com.mtcode.mblogapi.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mtcode.mblogapi.entity.Blog;
import com.mtcode.mblogapi.service.BlogService;
import com.mtcode.mblogapi.vo.BlogVO;
import com.mtcode.mblogapi.vo.Result;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/recommend")
    public Result recommend() {
        List<Blog> blogList = blogService.list(Wrappers.lambdaQuery(Blog.class)
                        .eq(Blog::getIsPublished, true)
                        .eq(Blog::getIsRecommend, true)
                        .orderByDesc(Blog::getCreateTime)
                        .last("limit 3"));

        return Result.ok(blogList);
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable("id") Long id) {
        BlogVO blogVO = blogService.homeDetail(id);
        if (blogVO == null) {
            return Result.fail("该博客不存在");
        } else {
            return Result.ok(blogVO);
        }
    }

    @GetMapping("/page/{pageNum}")
    public Result page(@PathVariable("pageNum") int pageNum, BlogVO blogVO) {
        Page<BlogVO> query = new Page<>();
        query.setCurrent(pageNum);
        IPage<BlogVO> iPage = blogService.homePage(query, blogVO);

        return Result.ok(iPage);
    }

    @GetMapping("/archives")
    public Result archives() {
        return Result.ok(blogService.archives());
    }

    @GetMapping("/count")
    public Result count() {
        return Result.ok(blogService.count(Wrappers.lambdaQuery(Blog.class).eq(Blog::getIsPublished, true)));
    }

    @GetMapping("/search/{query}")
    public Result search(@PathVariable("query") String query) {
        return Result.ok(blogService.search(query));
    }
}
