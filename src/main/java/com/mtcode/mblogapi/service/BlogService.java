package com.mtcode.mblogapi.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mtcode.mblogapi.entity.Blog;
import com.mtcode.mblogapi.vo.BlogVO;
import com.mtcode.mblogapi.vo.Result;

/**
 * @author TangMingZhang
 * @date 2022/3/20
 */
public interface BlogService extends IService<Blog> {

    /**
     * 更新或保存blog
     *
     * @param blogVO blogVO实体类
     * @return Result
     */
    Result updateOrSaveBlog(BlogVO blogVO);

    /**
     * 分页查询
     *
     * @param query 分页参数
     * @return 分页结果
     */
    IPage<BlogVO> pageVO(Page<BlogVO> query);
}
