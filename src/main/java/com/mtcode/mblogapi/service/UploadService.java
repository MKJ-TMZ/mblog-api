package com.mtcode.mblogapi.service;

import org.springframework.web.bind.annotation.RequestParam;

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
    String uploadToGitHub(String base64, String name);
}
