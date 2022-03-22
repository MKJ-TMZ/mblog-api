package com.mtcode.mblogapi.exception;

/**
 * @author TangMingZhang
 * @date 2022/3/19
 */
public class ParameterException extends RuntimeException{
    public ParameterException(String message) {
        super(message);
    }

    public ParameterException(String message, Throwable cause) {
        super(message, cause);
    }
}
