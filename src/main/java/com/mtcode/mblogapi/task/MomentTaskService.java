package com.mtcode.mblogapi.task;

import com.mtcode.mblogapi.constant.RedisConstant;
import com.mtcode.mblogapi.entity.Moment;
import com.mtcode.mblogapi.service.MomentService;
import com.mtcode.mblogapi.util.CacheUtils;
import com.mtcode.mblogapi.util.Func;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * @author TangMingZhang
 * @date 2022/4/12
 */
@Component
@AllArgsConstructor
public class MomentTaskService {

    private final MomentService momentService;

    @Scheduled(fixedDelay = 60000)
    @Transactional(rollbackFor = Exception.class)
    public void synLikeCount() {
        Set<String> keys = CacheUtils.getKeys(RedisConstant.LIKE_COUNT);
        if (keys != null && keys.size() > 0) {
            for (String key : keys) {
                if (Func.isNotEmptyAsString(key)) {
                    Integer likeCount = CacheUtils.getIntegerValue(key);
                    CacheUtils.delete(key);
                    String[] keySplit = key.split(":");
                    Long id = Long.valueOf(keySplit[keySplit.length - 1]);
                    Moment moment = momentService.getById(id);
                    if (moment != null && likeCount != null) {
                        moment.setLikeCount(moment.getLikeCount() + likeCount);
                    }
                    momentService.updateById(moment);
                }

            }
        }
    }
}
