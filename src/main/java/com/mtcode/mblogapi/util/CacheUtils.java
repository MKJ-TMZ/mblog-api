package com.mtcode.mblogapi.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author TangMingZhang
 * @date 2022/3/26
 */
@Component
public class CacheUtils {

    private static RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        CacheUtils.redisTemplate = redisTemplate;
    }

    /**
     * 向缓存中写入数据
     *
     * @param key key
     * @param value value
     */
    public static void setValue(String key, Object value) {
        redisTemplate.opsForValue().set(key, Objects.requireNonNull(JacksonUtils.writeValueAsString(value)), 7, TimeUnit.DAYS);
    }

    /**
     * 读取缓存中的数据
     *
     * @param key key
     * @param valueType 要返回的数据类型
     * @param <T> 要返回的数据类型
     * @return value
     */
    public static  <T> T getValue(String key, Class<T> valueType) {
        String value = (String) redisTemplate.opsForValue().get(key);
        if (Func.isEmptyAsString(value)) {
            return null;
        } else {
            return JacksonUtils.readValue((String) redisTemplate.opsForValue().get(key), valueType);
        }
    }

    /**
     * 删除缓存中的数据
     *
     * @param key key
     * @return 结果
     */
    public static boolean delete(String key) {
        return Boolean.TRUE.equals(redisTemplate.delete(key));
    }
}
