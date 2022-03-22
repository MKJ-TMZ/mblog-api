package com.mtcode.mblogapi.config;

import com.mtcode.mblogapi.entity.User;
import com.mtcode.mblogapi.exception.AuthException;
import com.mtcode.mblogapi.util.JacksonUtils;
import com.mtcode.mblogapi.util.JwtTokenUtils;
import com.mtcode.mblogapi.util.ResponseUtils;
import com.mtcode.mblogapi.vo.Result;
import com.mtcode.mblogapi.vo.UserLoginVO;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author TangMingZhang
 * @date 2022/3/18
 */
public class LoginFilter extends AbstractAuthenticationProcessingFilter {
    ThreadLocal<String> currentUsername = new ThreadLocal<>();

    protected LoginFilter(String defaultFilterProcessesUrl, AuthenticationManager authenticationManager) {
        super(defaultFilterProcessesUrl, authenticationManager);
    }

    /**
     * 登录认证
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @return Authentication
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {
        try {
            if (!"POST".equals(request.getMethod())) {
                throw new AuthException("请求方法错误");
            }

            User user = JacksonUtils.ReadValue(request.getInputStream(), User.class);
            assert user != null;
            currentUsername.set(user.getUsername());
            return getAuthenticationManager()
                    .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        } catch (AuthException e) {
            e.printStackTrace();
            ResponseUtils.ResponseOutJson(response, JacksonUtils.WriteValueAsString(Result.create(400, e.getMessage())));
            return null;
        } catch (AuthenticationException e) {
            e.printStackTrace();
            unsuccessfulAuthentication(request, response, e);
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            ResponseUtils.ResponseOutJson(response, JacksonUtils.WriteValueAsString(Result.create(400, "请求错误")));
            return null;
        }
    }

    /**
     * 认证成功
     *
     * @param request    HttpServletRequest
     * @param response   HttpServletResponse
     * @param chain      FilterChain
     * @param authResult Authentication
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult) throws IOException {
        String jwt = JwtTokenUtils.GenerateToken(authResult.getName(), authResult.getAuthorities());
        User user = (User) authResult.getPrincipal();
        UserLoginVO userLoginVO = new UserLoginVO(user.getId(), user.getNickname(), user.getAvatar(), user.getRole());
        Map<String, Object> map = new HashMap<>();
        map.put("user", userLoginVO);
        map.put("token", jwt);
        Result result = Result.ok("登录成功", map);
        ResponseUtils.ResponseOutJson(response, JacksonUtils.WriteValueAsString(result));
    }

    /**
     * 认证失败
     *
     * @param request   HttpServletRequest
     * @param response  HttpServletResponse
     * @param exception AuthenticationException
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException exception) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String msg = exception.getMessage();
        //登录不成功时，会抛出对应的异常
        switch (exception.getClass().getName()) {
            case "org.springframework.security.authentication.LockedException":
                msg = "账号被锁定";
                break;
            case "org.springframework.security.authentication.CredentialsExpiredException":
                msg = "密码过期";
                break;
            case "org.springframework.security.authentication.AccountExpiredException":
                msg = "账号过期";
                break;
            case "org.springframework.security.authentication.DisabledException":
                msg = "账号被禁用";
                break;
            case "org.springframework.security.authentication.BadCredentialsException":
                msg = "用户名或密码错误";
                break;
            default:
                msg = "登录失败";
        }
        ResponseUtils.ResponseOutJson(response, JacksonUtils.WriteValueAsString(Result.create(401, msg)));
    }
}
