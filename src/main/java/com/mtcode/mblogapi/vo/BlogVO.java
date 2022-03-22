package com.mtcode.mblogapi.vo;

import com.mtcode.mblogapi.entity.Blog;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * @author TangMingZhang
 * @date 2022/3/20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogVO extends Blog {

    /**
     * 分类名称
     */
    private String categoryName;

    /**
     * 标签名称
     */
    private Set<String> tagNameSet;
}
