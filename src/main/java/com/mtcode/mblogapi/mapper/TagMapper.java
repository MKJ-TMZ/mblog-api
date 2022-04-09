package com.mtcode.mblogapi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mtcode.mblogapi.entity.Tag;

import java.util.List;

/**
 * @author TangMingZhang
 * @date 2022/3/20
 */
public interface TagMapper extends BaseMapper<Tag> {

    List<Tag> selectTagsByBlogId(Long id);
}
