package com.mtcode.mblogapi.util;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author TangMingZhang
 * @date 2022/3/19
 */
public class ResponseUtils {

    /**
     * 向浏览器输出json格式的数据
     *
     * @param response HttpServletResponse
     * @param result 序列化后的字符串
     * @throws IOException IOException
     */
    public static void responseOutJson(HttpServletResponse response, String result) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.write(result);
        out.flush();
        out.close();
    }
}
