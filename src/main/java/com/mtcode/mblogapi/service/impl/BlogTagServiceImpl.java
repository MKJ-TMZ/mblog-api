package com.mtcode.mblogapi.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mtcode.mblogapi.entity.BlogTag;
import com.mtcode.mblogapi.entity.Tag;
import com.mtcode.mblogapi.exception.ParameterException;
import com.mtcode.mblogapi.mapper.BlogTagMapper;
import com.mtcode.mblogapi.service.BlogTagService;
import com.mtcode.mblogapi.service.TagService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author TangMingZhang
 * @date 2022/3/22
 */
@Service
@AllArgsConstructor
public class BlogTagServiceImpl extends ServiceImpl<BlogTagMapper, BlogTag> implements BlogTagService {

    private final TagService tagService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<BlogTag> saveBlogTagList(Long blogId, Set<String> tagNameSet) {
        if (blogId == null) {
            throw new ParameterException("参数错误");
        }

        List<Tag> tagList = tagService.saveLackTag(tagNameSet);

        List<BlogTag> blogTagList = new ArrayList<>();
        for (Tag tag : tagList) {
            BlogTag blogTag = new BlogTag();
            blogTag.setBlogId(blogId);
            blogTag.setTagId(tag.getId());
            blogTagList.add(blogTag);
        }
        remove(Wrappers.lambdaQuery(BlogTag.class).eq(BlogTag::getBlogId, blogId));
        saveBatch(blogTagList);

        return blogTagList;
    }
}
