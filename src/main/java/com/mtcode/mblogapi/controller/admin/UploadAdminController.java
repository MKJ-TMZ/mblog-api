package com.mtcode.mblogapi.controller.admin;

import com.mtcode.mblogapi.service.UploadService;
import com.mtcode.mblogapi.vo.Result;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * @author TangMingZhang
 * @date 2022/4/27
 */
@RestController
@RequestMapping("/admin/upload")
@AllArgsConstructor
public class UploadAdminController {

    private final UploadService uploadService;

    @PostMapping("")
    public Result save(@RequestParam String base64, @RequestParam String name) {
        return Result.ok("上传成功", uploadService.uploadToGitHub(base64, name));
    }
}
