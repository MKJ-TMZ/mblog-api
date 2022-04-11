package com.mtcode.mblogapi.task;

import com.mtcode.mblogapi.constant.RedisConstant;
import com.mtcode.mblogapi.entity.Blog;
import com.mtcode.mblogapi.service.BlogService;
import com.mtcode.mblogapi.util.CacheUtils;
import com.mtcode.mblogapi.util.Func;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * @author TangMingZhang
 * @date 2022/4/11
 */
@Component
@AllArgsConstructor
public class BlogTaskService {

    private final BlogService blogService;

    @Scheduled(fixedDelay = 60000)
    @Transactional(rollbackFor = Exception.class)
    public void synViewCount() {
        Set<String> keys = CacheUtils.getKeys(RedisConstant.VIEW_COUNT);
        if (keys != null && keys.size() > 0) {
            for (String key : keys) {
                if (Func.isNotEmptyAsString(key)) {
                    Integer viewCount = CacheUtils.getIntegerValue(key);
                    CacheUtils.delete(key);
                    String[] keySplit = key.split(":");
                    Long id = Long.valueOf(keySplit[keySplit.length - 1]);
                    Blog blog = blogService.getById(id);
                    if (blog != null && viewCount != null) {
                        blog.setViewCount(blog.getViewCount() + viewCount);
                    }
                    blogService.updateById(blog);
                }

            }
        }
    }
}
