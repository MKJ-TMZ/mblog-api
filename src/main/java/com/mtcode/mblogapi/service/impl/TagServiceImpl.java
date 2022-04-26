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
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author TangMingZhang
 * @date 2022/3/20
 */
@Service
@AllArgsConstructor
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

    @Override
    public List<Tag> saveLackTag(Set<String> tagNameSet) {
        if (tagNameSet == null || tagNameSet.size() <= 0) {
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
        String[] colors = {
                "red",
                "orange",
                "yellow",
                "olive",
                "green",
                "teal",
                "blue",
                "violet",
                "purple",
                "pink",
                "brown",
                "grey",
                "black"
        };
        if (tagNameSet.size() > 0) {
            Random random = new Random();
            for (String tagName : tagNameSet) {
                int colorNo = random.nextInt(colors.length - 1);
                Tag tag = new Tag();
                tag.setName(tagName);
                tag.setColor(colors[colorNo]);
                lackTagList.add(tag);
            }
            saveBatch(lackTagList);
            for (Tag lackTag : lackTagList) {
                CacheUtils.delete(RedisConstant.TAG + lackTag.getId());
            }
            CacheUtils.delete(RedisConstant.TAG + "list");
        }

        // 将已有的tag集合和没有的tag集合合并返回
        existentTagList.addAll(lackTagList);
        return existentTagList;
    }

    @Override
    public String getTagName(Long tagId) {
        Tag tag = CacheUtils.getValue(RedisConstant.TAG + tagId, Tag.class);
        if (tag == null) {
            Tag tagData = getOne(Wrappers.lambdaQuery(Tag.class).eq(Tag::getId, tagId));
            if (tagData != null) {
                CacheUtils.setValue(RedisConstant.TAG + tagData.getId(), tagData);
                return tagData.getName();
            } else {
                return "";
            }
        } else {
            return tag.getName();
        }
    }

    @Override
    public void updateOrSaveTag(Tag tag) {
        if (tag != null) {
            if (Func.isEmptyAsString(tag.getName())) {
                throw new ParameterException("参数错误");
            }

            if (Func.isEmptyAsString(tag.getColor())) {
                tag.setColor("teal");
            }
            if (tag.getId() == null) {
                save(tag);
            } else {
                updateById(tag);
            }
            CacheUtils.delete(RedisConstant.TAG + "list");
            CacheUtils.delete(RedisConstant.TAG + tag.getId());
        } else {
            throw new ParameterException("参数错误");
        }
    }

    @Override
    public List<Tag> getTagsByBlogId(Long id) {
        if (id != null) {
            List<Tag> tagList;
            tagList = baseMapper.selectTagsByBlogId(id);
            if (tagList == null) {
                tagList = new ArrayList<>();
            }
            return tagList;
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public Tag getTagById(Long id) {
        Tag tagCache = CacheUtils.getValue(RedisConstant.TAG + id, Tag.class);
        if (tagCache == null) {
            Tag tag = getById(id);
            if (tag == null) {
                return null;
            }
            CacheUtils.setValue(RedisConstant.TAG + id, tag);
            return tag;
        } else {
            return tagCache;
        }
    }
}
