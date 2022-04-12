package com.mtcode.mblogapi.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.mtcode.mblogapi.entity.About;
import com.mtcode.mblogapi.service.AboutService;
import com.mtcode.mblogapi.util.MarkdownUtils;
import com.mtcode.mblogapi.vo.Result;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author TangMingZhang
 * @date 2022/4/12
 */
@RestController
@RequestMapping("/about")
@AllArgsConstructor
public class AboutController {

    private final AboutService aboutService;

    @GetMapping("")
    public Result get() {
        About about = aboutService.getOne(Wrappers.lambdaQuery());
        if (about == null) {
            about = new About();
        } else {
            about.setContent(MarkdownUtils.markdownToHtml(about.getContent()));
        }
        return Result.ok(about);
    }
}
