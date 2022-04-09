package com.mtcode.mblogapi.vo;

import com.mtcode.mblogapi.entity.Blog;
import com.mtcode.mblogapi.entity.Category;
import com.mtcode.mblogapi.entity.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

/**
 * @author TangMingZhang
 * @date 2022/3/20
 */
@EqualsAndHashCode(callSuper = true)
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

    /**
     * 标签
     */
    private List<Tag> tagList;
}
