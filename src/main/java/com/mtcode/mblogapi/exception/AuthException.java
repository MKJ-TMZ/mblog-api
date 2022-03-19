package com.mtcode.mblogapi.exception;

/**
 * @author TangMingZhang
 * @date 2022/3/19
 */
public class AuthException extends RuntimeException{
    public AuthException(String message) {
        super(message);
    }

    public AuthException(String message, Throwable cause) {
        super(message, cause);
    }
}
