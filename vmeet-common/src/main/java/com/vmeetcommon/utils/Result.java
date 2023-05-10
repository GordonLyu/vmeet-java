package com.vmeetcommon.utils;

import com.alibaba.fastjson2.JSON;
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

    public Result(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static Result success(String msg, Object data){
        return new Result(200, msg, data);
    }

    public static Result success(Integer code, String msg, Object data){
        return new Result(code, msg, data);
    }

    public static Result fail(Integer code, String msg, Object data){
        return new Result(code, msg, data);
    }

    public String toJSON(){
        return JSON.toJSONString(this);
    }
}
