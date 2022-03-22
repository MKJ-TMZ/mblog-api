package com.mtcode.mblogapi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mtcode.mblogapi.entity.Category;
import com.mtcode.mblogapi.mapper.CategoryMapper;
import com.mtcode.mblogapi.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author TangMingZhang
 * @date 2022/3/22
 */
@Service
@AllArgsConstructor
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
}
