package com.mtcode.mblogapi.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mtcode.mblogapi.constant.RedisConstant;
import com.mtcode.mblogapi.entity.Tag;
import com.mtcode.mblogapi.service.TagService;
import com.mtcode.mblogapi.util.CacheUtils;
import com.mtcode.mblogapi.vo.Result;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author TangMingZhang
 * @date 2022/3/22
 */
@RestController
@RequestMapping("/admin/tag")
@AllArgsConstructor
public class TagAdminController {

    private final TagService tagService;

    @GetMapping("/list")
    public Result list() {
        return Result.ok("成功", tagService.list());
    }

    @GetMapping("/page")
    public Result page(Page<Tag> query) {
        return Result.ok(tagService.page(query));
    }

    @PostMapping("")
    public Result save(@RequestBody Tag tag) {
        tagService.updateOrSaveTag(tag);
        return Result.ok();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable("id") Long id) {
        boolean removeResult = tagService.removeById(id);
        if (removeResult) {
            CacheUtils.delete(RedisConstant.TAG + "list");
            CacheUtils.delete(RedisConstant.TAG + id);
            return Result.ok();
        } else {
            return Result.create(400, "删除失败");
        }
    }
}
