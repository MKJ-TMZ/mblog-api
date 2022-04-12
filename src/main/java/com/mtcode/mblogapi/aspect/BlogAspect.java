package com.mtcode.mblogapi.aspect;

import com.mtcode.mblogapi.constant.RedisConstant;
import com.mtcode.mblogapi.util.CacheUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author TangMingZhang
 * @date 2022/4/11
 */
@Aspect
@Component
public class BlogAspect {

    @Pointcut("execution(* com.mtcode.mblogapi.controller.BlogController.detail(..))")
    public void detail() {};

    @AfterReturning("detail()")
    public void detailAfterReturningAdvice(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        Long id = (Long) args[0];
        CacheUtils.incr(RedisConstant.VIEW_COUNT + id);
    }
}
