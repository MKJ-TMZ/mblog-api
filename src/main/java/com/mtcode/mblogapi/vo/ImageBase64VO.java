package com.mtcode.mblogapi.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author TangMingZhang
 * @date 2022/5/2
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageBase64VO {

    /**
     * 图片base64编码
     */
    private String base64;

    /**
     * 图片名称
     */
    private String name;
}
