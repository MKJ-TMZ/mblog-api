package com.mtcode.mblogapi.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mtcode.mblogapi.constant.RedisConstant;
import com.mtcode.mblogapi.entity.Blog;
import com.mtcode.mblogapi.service.BlogService;
import com.mtcode.mblogapi.util.CacheUtils;
import com.mtcode.mblogapi.vo.BlogVO;
import com.mtcode.mblogapi.vo.Result;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author TangMingZhang
 * @date 2022/3/20
 */
@RestController
@RequestMapping("/admin/blog")
@AllArgsConstructor
public class BlogAdminController {

    private final BlogService blogService;

    @PostMapping("")
    public Result saveBlog(@RequestBody BlogVO blogVO) {
        return Result.ok("保存成功", blogService.updateOrSaveBlog(blogVO));
    }

    @GetMapping("/page")
    public Result page(Page<BlogVO> query, BlogVO blogVO) {
        return Result.ok("成功", blogService.pageVO(query, blogVO));
    }

    @PostMapping("/update")
    public Result update(@RequestBody Blog blog) {
        blogService.update(blog);
        return Result.ok();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable("id") Long id) {
        boolean removeResult = blogService.removeById(id);
        if (removeResult) {
            CacheUtils.delete(RedisConstant.BLOG + id);
        }
        return new Result(removeResult);
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable("id") Long id) {
        return Result.ok(blogService.detail(id));
    }
}
