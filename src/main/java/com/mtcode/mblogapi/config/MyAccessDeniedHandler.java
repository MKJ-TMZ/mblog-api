package com.mtcode.mblogapi.config;

import com.mtcode.mblogapi.util.JacksonUtils;
import com.mtcode.mblogapi.util.ResponseUtils;
import com.mtcode.mblogapi.vo.Result;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author TangMingZhang
 * @date 2022/3/22
 */
@Component
public class MyAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        ResponseUtils.responseOutJson(response, JacksonUtils.writeValueAsString(Result.create(403, "抱歉，您没有访问该接口的权限")));
    }
}
