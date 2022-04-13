package com.mtcode.mblogapi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mtcode.mblogapi.entity.Blog;
import com.mtcode.mblogapi.vo.BlogVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author TangMingZhang
 * @date 2022/3/20
 */
public interface BlogMapper extends BaseMapper<Blog> {

    /**
     * 分页查询
     *
     * @param query 分页参数
     * @param blogVO 查询条件
     * @return list集合
     */
    List<BlogVO> selectPageVO(@Param("page") IPage<BlogVO> query, @Param("req") BlogVO blogVO);

    /**
     * 博客主页分页查询
     *
     * @param query 分页参数
     * @param blogVO 查询条件
     * @return list集合
     */
    List<BlogVO> selectHomePage(@Param("page") IPage<BlogVO> query, @Param("req") BlogVO blogVO);

    /**
     * 通过标题获取博客列表
     *
     * @param query 搜索条件
     * @return blogList
     */
    List<Blog> search(@Param("query") String query);
}
