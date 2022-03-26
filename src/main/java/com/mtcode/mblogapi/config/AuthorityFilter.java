package com.mtcode.mblogapi.config;

import com.mtcode.mblogapi.util.JacksonUtils;
import com.mtcode.mblogapi.util.JwtTokenUtils;
import com.mtcode.mblogapi.util.ResponseUtils;
import com.mtcode.mblogapi.vo.Result;
import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author TangMingZhang
 * @date 2022/3/18
 */
public class AuthorityFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //忽略后台管理路径外的请求
        if (!request.getRequestURI().startsWith("/admin")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        String jwt = request.getHeader("Authorization");
        if (JwtTokenUtils.TokenIsExist(jwt)) {
            try {
                Claims claims = JwtTokenUtils.GetTokenBody(jwt);
                String username = claims.getSubject();
                List<GrantedAuthority> authorities =
                        AuthorityUtils.commaSeparatedStringToAuthorityList(claims.get("authorities").toString());
                UsernamePasswordAuthenticationToken token =
                        new UsernamePasswordAuthenticationToken(username, null, authorities);
                SecurityContextHolder.getContext().setAuthentication(token);
            } catch (Exception e) {
                e.printStackTrace();
                Result result = Result.create(403, "凭证已失效，请重新登录！");
                ResponseUtils.responseOutJson(response, JacksonUtils.writeValueAsString(result));
                return;
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
