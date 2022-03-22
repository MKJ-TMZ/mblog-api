package com.mtcode.mblogapi.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mtcode.mblogapi.entity.Tag;
import com.mtcode.mblogapi.exception.ParameterException;
import com.mtcode.mblogapi.mapper.TagMapper;
import com.mtcode.mblogapi.service.TagService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author TangMingZhang
 * @date 2022/3/20
 */
@Service
@AllArgsConstructor
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

    @Override
    public List<Tag> saveLackTag(Set<String> tagNameSet) {
        if (tagNameSet == null) {
            throw new ParameterException("参数错误");
        }

        // 在数据库中找出已有的tag
        List<Tag> existentTagList = list(Wrappers.lambdaQuery(Tag.class).in(Tag::getName, tagNameSet));

        // 将已有的tag名称放在set集合中
        Set<String> existentTagNameSet = new HashSet<>();
        for (Tag existentTag : existentTagList) {
            existentTagNameSet.add(existentTag.getName());
        }
        // 找出数据库中没有的tag名称
        Set<String> lackTagSet = new HashSet<>();
        for (String tagName : tagNameSet) {
            if (existentTagNameSet.add(tagName)) {
                lackTagSet.add(tagName);
            }
        }

        // 新增数据库中没有的tag
        List<Tag> lackTagList = new ArrayList<>();
        for (String lackTag : lackTagSet) {
            Tag tag = new Tag();
            tag.setName(lackTag);
            lackTagList.add(tag);
        }
        saveBatch(lackTagList);

        // 将已有的tag集合和没有的tag集合合并返回
        existentTagList.addAll(lackTagList);
        return existentTagList;
    }
}
