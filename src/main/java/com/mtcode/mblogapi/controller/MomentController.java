package com.mtcode.mblogapi.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mtcode.mblogapi.constant.RedisConstant;
import com.mtcode.mblogapi.entity.Moment;
import com.mtcode.mblogapi.service.MomentService;
import com.mtcode.mblogapi.util.CacheUtils;
import com.mtcode.mblogapi.vo.Result;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author TangMingZhang
 * @date 2022/4/12
 */
@RestController
@RequestMapping("/moment")
@AllArgsConstructor
public class MomentController {

    private final MomentService momentService;

    @GetMapping("/page/{pageNum}")
    public Result page(@PathVariable("pageNum") int pageNum) {
        Page<Moment> query = new Page<>();
        query.setCurrent(pageNum);
        IPage<Moment> iPage = momentService.homePage(query);

        return Result.ok(iPage);
    }

    @GetMapping("/like/{id}")
    public Result like(@PathVariable("id") Long id) {
        CacheUtils.incr(RedisConstant.LIKE_COUNT + id);
        return new Result(true);
    }
}
