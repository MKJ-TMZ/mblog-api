package com.mtcode.mblogapi.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mtcode.mblogapi.service.BlogService;
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

    @PostMapping("/save")
    public Result saveBlog(@RequestBody BlogVO blogVO) {
        return blogService.updateOrSaveBlog(blogVO);
    }

    @GetMapping("/page")
    public Result page(Page<BlogVO> query) {
        return Result.ok("成功", blogService.pageVO(query));
    }
}
