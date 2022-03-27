package com.mtcode.mblogapi.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mtcode.mblogapi.entity.Category;
import com.mtcode.mblogapi.service.CategoryService;
import com.mtcode.mblogapi.vo.Result;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
        return Result.ok(categoryService.list());
    }

    @GetMapping("/page")
    public Result page(Page<Category> query) {
        return Result.ok(categoryService.page(query));
    }

    @PostMapping("/save")
    public Result save(@RequestBody Category category) {
        categoryService.updateOrSaveCategory(category);
        return Result.ok();
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Long id) {
        boolean removeResult = categoryService.removeById(id);
        if (removeResult) {
            return Result.ok();
        } else {
            return Result.create(400, "删除失败");
        }
    }
}
