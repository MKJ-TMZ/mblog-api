package com.mtcode.mblogapi.controller.admin;

import com.mtcode.mblogapi.service.TagService;
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
@RequestMapping("/admin/tag")
@AllArgsConstructor
public class TagAdminController {

    private TagService tagService;

    @GetMapping("/list")
    public Result list() {
        return Result.ok("成功", tagService.list());
    }
}
