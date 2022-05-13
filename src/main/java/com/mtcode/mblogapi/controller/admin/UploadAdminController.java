package com.mtcode.mblogapi.controller.admin;

import com.mtcode.mblogapi.service.UploadService;
import com.mtcode.mblogapi.util.PicUtils;
import com.mtcode.mblogapi.vo.ImageBase64VO;
import com.mtcode.mblogapi.vo.Result;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import java.io.IOException;

/**
 * @author TangMingZhang
 * @date 2022/4/27
 */
@RestController
@RequestMapping("/admin/upload")
@AllArgsConstructor
public class UploadAdminController {

    private final UploadService uploadService;

    @PostMapping("/base64")
    public Result save(@RequestBody ImageBase64VO imageBase64VO) {
        return Result.ok("上传成功",
                uploadService.uploadToGitHubByBase64(imageBase64VO.getBase64(), imageBase64VO.getName()));
    }

    @PostMapping("/file")
    public Result save(@RequestParam("file") MultipartFile file) throws IOException {
        return Result.ok("上传成功",
                uploadService.uploadToGitHubByFile(file, file.getOriginalFilename()));
    }
}
