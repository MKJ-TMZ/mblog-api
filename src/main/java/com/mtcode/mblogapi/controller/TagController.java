package com.mtcode.mblogapi.controller;

import com.mtcode.mblogapi.entity.Tag;
import com.mtcode.mblogapi.service.TagService;
import com.mtcode.mblogapi.vo.Result;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author TangMingZhang
 * @date 2022/4/9
 */
@RestController
@RequestMapping("/tag")
@AllArgsConstructor
public class TagController {

    private final TagService tagService;

    @GetMapping("/list")
    public Result list() {
        List<Tag> tagList = tagService.list();

        return Result.ok(tagList);
    }
}
