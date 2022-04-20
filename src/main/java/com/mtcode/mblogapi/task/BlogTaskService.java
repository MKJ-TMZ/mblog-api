package com.mtcode.mblogapi.task;

import com.mtcode.mblogapi.constant.RedisConstant;
import com.mtcode.mblogapi.converter.BlogConverter;
import com.mtcode.mblogapi.entity.Blog;
import com.mtcode.mblogapi.service.BlogService;
import com.mtcode.mblogapi.util.CacheUtils;
import com.mtcode.mblogapi.util.Func;
import com.mtcode.mblogapi.util.MarkdownUtils;
import com.mtcode.mblogapi.vo.BlogVO;
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
                    BlogVO blogVO = blogService.detail(id);
                    if (blogVO != null) {
                        if (viewCount != null && viewCount > 0) {
                            blogVO.setViewCount(blogVO.getViewCount() + viewCount);
                        }
                        blogService.updateById(blogVO);
                        blogVO.setContent(MarkdownUtils.markdownToHtml(blogVO.getContent()));
                        blogVO.setDescription(MarkdownUtils.markdownToHtml(blogVO.getDescription()));
                        CacheUtils.setValue(RedisConstant.BLOG + id, blogVO);
                    }
                }

            }
        }
    }
}
