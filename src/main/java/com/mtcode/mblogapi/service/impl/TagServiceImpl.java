package com.mtcode.mblogapi.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mtcode.mblogapi.constant.RedisConstant;
import com.mtcode.mblogapi.entity.Tag;
import com.mtcode.mblogapi.exception.ParameterException;
import com.mtcode.mblogapi.mapper.TagMapper;
import com.mtcode.mblogapi.service.TagService;
import com.mtcode.mblogapi.util.CacheUtils;
import com.mtcode.mblogapi.util.Func;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.mtcode.mblogapi.util.JacksonUtils.writeValueAsString;

/**
 * @author TangMingZhang
 * @date 2022/3/20
 */
@Service
@AllArgsConstructor
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

    private final CacheUtils cacheUtils;

    @Override
    public List<Tag> saveLackTag(Set<String> tagNameSet) {
        if (tagNameSet == null) {
            return new ArrayList<>();
        }

        // 在数据库中找出已有的tag
        List<Tag> existentTagList = list(Wrappers.lambdaQuery(Tag.class).in(Tag::getName, tagNameSet));
        // 将已有的tag名称放在set集合中
        Set<String> existentTagNameSet = new HashSet<>();
        for (Tag existentTag : existentTagList) {
            existentTagNameSet.add(existentTag.getName());
        }

        // 找出数据库中没有的tag名称
        tagNameSet.removeIf(tagName -> !existentTagNameSet.add(tagName));
        // 新增数据库中没有的tag
        List<Tag> lackTagList = new ArrayList<>();
        if (tagNameSet.size() > 0) {
            for (String tagName : tagNameSet) {
                Tag tag = new Tag();
                tag.setName(tagName);
                tag.setColor("teal");
                lackTagList.add(tag);
            }
            saveBatch(lackTagList);

            // 更新redis缓存
            for (Tag lackTag : lackTagList) {
                if (lackTag != null) {
                    cacheUtils.setValue(RedisConstant.TAG + lackTag.getId(), lackTag);
                }
            }
        }

        // 将已有的tag集合和没有的tag集合合并返回
        existentTagList.addAll(lackTagList);
        return existentTagList;
    }

    @Override
    public String getTagName(Long tagId) {
        Tag tag = cacheUtils.getValue(RedisConstant.TAG + tagId, Tag.class);
        if (tag == null) {
            Tag tagData = getOne(Wrappers.lambdaQuery(Tag.class).eq(Tag::getId, tagId));
            if (tagData != null) {
                cacheUtils.setValue(RedisConstant.TAG + tagData.getId(), tagData);
                return tagData.getName();
            } else {
                return "";
            }
        } else {
            return tag.getName();
        }
    }
}
