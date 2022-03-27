package com.mtcode.mblogapi.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author TangMingZhang
 * @date 2022/3/16
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {

    private Integer code;
    private String msg;
    private Object data;

    private Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static Result ok(String msg, Object data) {
        return new Result(200, msg, data);
    }

    public static Result ok(String msg) {
        return new Result(200, msg);
    }

    public static Result ok() {
        return new Result(200, "请求成功");
    }

    public static Result ok(Object data) {
        return new Result(200, "请求成功", data);
    }

    public static Result error(String msg) {
        return new Result(500, msg);
    }

    public static Result error() {
        return new Result(500, "异常错误");
    }

    public static Result create(Integer code, String msg, Object data) {
        return new Result(code, msg, data);
    }

    public static Result create(Integer code, String msg) {
        return new Result(code, msg);
    }
}
