package com.mtcode.mblogapi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mtcode.mblogapi.entity.BlogTag;

import java.util.List;
import java.util.Set;

/**
 * @author TangMingZhang
 * @date 2022/3/22
 */
public interface BlogTagService extends IService<BlogTag> {

    /**
     * 保存多个
     *
     * @param blogId 博客id
     * @param tagNameList 标签名list
     * @return 所有保存的BlogTag
     */
    List<BlogTag> saveBlogTagList(Long blogId, Set<String> tagNameList);
}
