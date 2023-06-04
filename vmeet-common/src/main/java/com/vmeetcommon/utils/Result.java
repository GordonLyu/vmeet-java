package com.vmeetcommon.utils;

import com.alibaba.fastjson2.JSON;
import com.vmeetcommon.exception.ExceptionEnum;
import lombok.Data;

/**
 * @author LvXinming
 * @since 2023/2/27
 */

@Data
public class Result {
    private Integer code;
    private String msg;
    private Object data;

    public Result() {
    }
    public Result(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static Result success(String msg, Object data){
        return new Result(200, msg, data);
    }
    public static Result success() {
        return new Result(200, "ok", null);
    }
    public static Result success(Object data) {
        return success(200, "请求成功", data);
    }

    public static Result success(Integer code, String msg, Object data){
        return new Result(code, msg, data);
    }

    public static Result fail(Integer code, String msg, Object data){
        return new Result(code, msg, data);
    }
    public static Result fail(Integer code, String msg){
        return new Result(code, msg, null);
    }
    public static Result fail(String message) {
        return fail(400, message, null);
    }
    public static Result fail(ExceptionEnum errorInfo) {
        Result rb = new Result();
        rb.setCode(Integer.valueOf(errorInfo.getResultCode()));
        rb.setMsg(errorInfo.getResultMsg());
        rb.setData(null);
        return rb;
    }

    public String toJSON(){
        return JSON.toJSONString(this);
    }
}
