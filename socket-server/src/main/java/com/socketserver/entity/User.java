package com.socketserver.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author LvXinming
 * @since 2023/3/18
 * <p>用户实体类，用于测试，并非用于实际中，莫BB</p>
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer id;
    private Integer to;
    private String content;
    private String type;

    public User(Integer id, String msg, String type){
        this.id = id;
        this.content = msg;
        this.type = type;
    }
}
