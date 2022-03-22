package com.mtcode.mblogapi.controller.admin;

import com.mtcode.mblogapi.service.CategoryService;
import com.mtcode.mblogapi.vo.Result;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author TangMingZhang
 * @date 2022/3/22
 */
@RestController
@RequestMapping("/admin/category")
@AllArgsConstructor
public class CategoryAdminController {

    private CategoryService categoryService;

    @GetMapping("/list")
    public Result list() {
        return Result.ok("成功", categoryService.list());
    }
}
