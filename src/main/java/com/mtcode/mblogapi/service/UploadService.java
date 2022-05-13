package com.mtcode.mblogapi.service;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author TangMingZhang
 * @date 2022/4/27
 */
public interface UploadService {

    /**
     * 上传图片到GitHub
     *
     * @param base64 图片的base64编码
     * @param name 图片名称
     * @return 图片路径
     */
    String uploadToGitHubByBase64(String base64, String name);

    /**
     * 上传图片到GitHub
     *
     * @param file 图片流
     * @param name 图片名称
     * @return 图片路径
     */
    String uploadToGitHubByFile(MultipartFile file, String name) throws IOException;
}
