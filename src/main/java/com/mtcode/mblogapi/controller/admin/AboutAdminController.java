package com.mtcode.mblogapi.controller.admin;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.mtcode.mblogapi.entity.About;
import com.mtcode.mblogapi.service.AboutService;
import com.mtcode.mblogapi.vo.Result;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author TangMingZhang
 * @date 2022/3/31
 */
@RestController
@RequestMapping("/admin/about")
@AllArgsConstructor
public class AboutAdminController {

    private final AboutService aboutService;

    @PostMapping("/save")
    public Result save(@RequestBody About about) {
        aboutService.updateOrSaveAbout(about);
        return Result.ok();
    }

    @GetMapping("/detail")
    public Result detail() {
        About about = aboutService.getOne(Wrappers.lambdaQuery());
        if (about == null) {
            about = new About();
        }
        return Result.ok(about);
    }
}
