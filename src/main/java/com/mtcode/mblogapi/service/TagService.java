package com.mtcode.mblogapi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mtcode.mblogapi.entity.Tag;

import java.util.List;
import java.util.Set;

/**
 * @author TangMingZhang
 * @date 2022/3/20
 */
public interface TagService extends IService<Tag> {

    /**
     * 保存集合中数据库没有的标签
     *
     * @param tagNameSet 标签名集合
     * @return TagList
     */
    List<Tag> saveLackTag(Set<String> tagNameSet);

    /**
     * 读取缓存中的标签名
     *
     * @param tagId 标签id
     * @return 标签名
     */
    String getTagName(Long tagId);
}
