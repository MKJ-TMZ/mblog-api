package com.mtcode.mblogapi.util;

import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static com.mtcode.mblogapi.util.JacksonUtils.readValue;
import static com.mtcode.mblogapi.util.JacksonUtils.writeValueAsString;

/**
 * @author TangMingZhang
 * @date 2022/3/26
 */
@Component
@AllArgsConstructor
public class CacheUtils {

    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 向缓存中写入数据
     *
     * @param key key
     * @param value value
     */
    public void setValue(String key, Object value) {
        redisTemplate.opsForValue().set(key, Objects.requireNonNull(writeValueAsString(value)));
    }

    /**
     * 读取缓存中的数据
     *
     * @param key key
     * @param valueType 要返回的数据类型
     * @param <T> 要返回的数据类型
     * @return value
     */
    public <T> T getValue(String key, Class<T> valueType) {
        String value = (String) redisTemplate.opsForValue().get(key);
        if (Func.isEmptyAsString(value)) {
            return null;
        } else {
            return readValue((String) redisTemplate.opsForValue().get(key), valueType);
        }
    }
}
