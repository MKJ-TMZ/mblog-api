package com.mtcode.mblogapi.config;

import com.mtcode.mblogapi.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author TangMingZhang
 * @date 2022/3/16
 */
@Configuration
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;
    private final MyAuthenticationEntryPoint myAuthenticationEntryPoint;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService((UserDetailsService) userService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //禁用 csrf 防御
        http.csrf().disable()
                //开启跨域支持
                .cors()
                .and()
                //基于Token，不创建会话
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                //放行获取网页标题后缀的请求
                .antMatchers("/admin/webTitleSuffix").permitAll()
                //任何 /admin 开头的路径下的请求都需要经过JWT验证
                .antMatchers(HttpMethod.GET, "/admin/**").hasAnyRole("admin")
                .antMatchers("/admin/**").hasRole("admin")
                //其它路径全部放行
                .anyRequest().permitAll()
                .and()
                // 自定义登录过滤器
                .addFilterBefore(new LoginFilter("/admin/login", authenticationManager()), UsernamePasswordAuthenticationFilter.class)
                // 自定义权限过滤器
                .addFilterBefore(new AuthorityFilter(), UsernamePasswordAuthenticationFilter.class)
                //未登录时，返回json，在前端执行重定向
                .exceptionHandling().authenticationEntryPoint(myAuthenticationEntryPoint);
    }
}
