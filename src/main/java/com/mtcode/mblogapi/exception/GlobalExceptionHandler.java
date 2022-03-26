package com.mtcode.mblogapi.exception;

import com.mtcode.mblogapi.vo.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author TangMingZhang
 * @date 2022/3/22
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public Result globalException(Exception e) {
        e.printStackTrace();
        return Result.create(500, "服务器异常");
    }
}
