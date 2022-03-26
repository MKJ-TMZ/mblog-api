package com.mtcode.mblogapi.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.List;

/**
 * @author TangMingZhang
 * @date 2022/3/16
 */
public class JacksonUtils {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String writeValueAsString(Object value) {
        try {
            return objectMapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> T readValue(String content, Class<T> valueType) {
        try {
            return objectMapper.readValue(content, valueType);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> T readValue(InputStream src, Class<T> valueType) {
        try {
            return objectMapper.readValue(src, valueType);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> T readCollection(String src, Class<? extends Collection> collectionClass, Class<?> elementClass) {
        try {
            JavaType javaType = objectMapper.getTypeFactory().constructCollectionType(collectionClass, elementClass);
            return objectMapper.readValue(src, javaType);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
