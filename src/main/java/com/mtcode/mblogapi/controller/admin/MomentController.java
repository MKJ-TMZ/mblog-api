package com.mtcode.mblogapi.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mtcode.mblogapi.entity.Moment;
import com.mtcode.mblogapi.service.MomentService;
import com.mtcode.mblogapi.vo.Result;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author TangMingZhang
 * @date 2022/3/28
 */
@RestController
@RequestMapping("/admin/moment")
@AllArgsConstructor
public class MomentController {

    private final MomentService momentService;

    @PostMapping("/save")
    public Result saveBlog(@RequestBody Moment moment) {
        momentService.updateOrSaveMoment(moment);
        return Result.ok();
    }

    @PostMapping("/update")
    public Result update(@RequestBody Moment moment) {
        momentService.update(moment);
        return Result.ok();
    }

    @GetMapping("/page")
    public Result page(Page<Moment> query) {
        return Result.ok(momentService.page(query));
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Long id) {
        boolean removeResult = momentService.removeById(id);
        if (removeResult) {
            return Result.ok();
        } else {
            return Result.create(400, "删除失败");
        }
    }

    @GetMapping("/detail/{id}")
    public Result detail(@PathVariable("id") Long id) {
        return Result.ok(momentService.detail(id));
    }
}
