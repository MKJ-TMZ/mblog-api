package com.mtcode.mblogapi.exception;

/**
 * @author TangMingZhang
 * @date 2022/3/27
 */
public class NullException extends RuntimeException {

    public NullException(String message) {
        super(message);
    }

    public NullException(String message, Throwable cause) {
        super(message, cause);
    }
}
