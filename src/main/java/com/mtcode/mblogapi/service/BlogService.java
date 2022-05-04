package com.mtcode.mblogapi.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mtcode.mblogapi.entity.Blog;
import com.mtcode.mblogapi.vo.BlogVO;
import com.mtcode.mblogapi.vo.Result;

import java.util.List;
import java.util.Map;

/**
 * @author TangMingZhang
 * @date 2022/3/20
 */
public interface BlogService extends IService<Blog> {

    /**
     * 更新或保存blog
     *
     * @param blogVO blogVO实体类
     */
    String updateOrSaveBlog(BlogVO blogVO);

    /**
     * 分页查询
     *
     * @param query 分页参数
     * @return 分页结果
     */
    IPage<BlogVO> pageVO(Page<BlogVO> query, BlogVO blogVO);

    /**
     * 更新博客
     *
     * @param blog blog实体类
     */
    void update(Blog blog);

    /**
     * 博客详情
     *
     * @param id 博客id
     * @return blogVO实体类
     */
    BlogVO detail(Long id);

    /**
     * 博客主页分页查询
     *
     * @param query 分页参数
     * @param blogVO 查询条件
     * @return 分页结果
     */
    IPage<BlogVO> homePage(Page<BlogVO> query, BlogVO blogVO);

    /**
     * 主页博客详情
     *
     * @param id 博客id
     * @return blogVO实体类
     */
    BlogVO homeDetail(Long id);

    /**
     * 获取博客归档
     * @return 归档信息
     */
    List<Map<String, Object>> archives();

    /**
     * 通过标题获取博客列表
     *
     * @param query 搜索条件
     * @return blogList
     */
    List<Blog> search(String query);
}
