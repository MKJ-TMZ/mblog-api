package com.mtcode.mblogapi.util;

/**
 * @author TangMingZhang
 * @date 2022/3/18
 */
public class Func {
    /**
     * 判断字符串是否为空
     *
     * @param str 待校验字符串
     * @return 校验结果
     */
    public static boolean isEmptyAsString(String... str) {
        for (String s : str) {
            if (s == null || "".equals(s.trim())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断字符串是否非空
     *
     * @param str 待校验字符串
     * @return 校验结果
     */
    public static boolean isNotEmptyAsString(String... str) {
        return !isEmptyAsString(str);
    }
}
