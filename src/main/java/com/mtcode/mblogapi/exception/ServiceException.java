package com.mtcode.mblogapi.exception;

/**
 * @author TangMingZhang
 * @date 2022/4/28
 */
public class ServiceException extends RuntimeException {

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
