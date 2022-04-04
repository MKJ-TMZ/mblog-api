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

    public Result(boolean result) {
        if (result) {
            this.code = 200;
            this.msg = "请求成功";
        } else {
            this.code = 400;
            this.msg = "失败";
        }
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

    public static Result fail(String msg, Object data) {
        return new Result(400, msg, data);
    }

    public static Result fail(String msg) {
        return new Result(400, msg);
    }

    public static Result fail() {
        return new Result(400, "失败");
    }

    public static Result fail(Object data) {
        return new Result(400, "失败", data);
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
