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

    @ExceptionHandler(value = ParameterException.class)
    public Result ParameterException(ParameterException e) {
        return Result.create(400, e.getMessage());
    }

    @ExceptionHandler(value = AuthException.class)
    public Result AuthException(AuthException e) {
        return Result.create(401, e.getMessage());
    }

    @ExceptionHandler(value = ServiceException.class)
    public Result ServiceException(ServiceException e) {
        return Result.create(400, e.getMessage());
    }

    @ExceptionHandler(value = NullException.class)
    public Result OtherException(NullException e) {
        return Result.create(400, e.getMessage());
    }


}
