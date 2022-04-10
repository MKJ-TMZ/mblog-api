package com.mtcode.mblogapi.controller;

import com.mtcode.mblogapi.entity.Category;
import com.mtcode.mblogapi.service.CategoryService;
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
@RequestMapping("/category")
@AllArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/list")
    public Result list() {
        List<Category> categoryList = categoryService.list();

        return Result.ok(categoryList);
    }

    @GetMapping("/{id}")
    public Result getCategory(@PathVariable("id") Long id) {
        return Result.ok("成功", categoryService.getCategoryName(id));
    }
}
