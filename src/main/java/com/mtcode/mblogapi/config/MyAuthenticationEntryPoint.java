package com.mtcode.mblogapi.config;

import com.mtcode.mblogapi.util.JacksonUtils;
import com.mtcode.mblogapi.util.ResponseUtils;
import com.mtcode.mblogapi.vo.Result;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author TangMingZhang
 * @date 2022/3/16
 */
@Component
public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        Result result = Result.create(403, "请登录");
        ResponseUtils.ResponseOutJson(response, JacksonUtils.WriteValueAsString(result));
    }
}
