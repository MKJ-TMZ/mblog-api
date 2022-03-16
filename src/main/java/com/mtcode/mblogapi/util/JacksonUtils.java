package com.mtcode.mblogapi.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author TangMingZhang
 * @date 2022/3/16
 */
public class JacksonUtils {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String WriteValueAsString(Object value) {
        try {
            return objectMapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "";
        }
    }
}
