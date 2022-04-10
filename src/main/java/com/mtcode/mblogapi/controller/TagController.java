package com.mtcode.mblogapi.controller;

import com.mtcode.mblogapi.entity.Tag;
import com.mtcode.mblogapi.service.TagService;
import com.mtcode.mblogapi.vo.Result;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        return Result.ok(tagService.list());
    }

    @GetMapping("/{id}")
    public Result getTag(@PathVariable("id") Long id) {
        Tag tag = tagService.getTagById(id);
        if (tag == null) {
            return Result.fail("标签不存在");
        }
        return Result.ok(tag);
    }
}
