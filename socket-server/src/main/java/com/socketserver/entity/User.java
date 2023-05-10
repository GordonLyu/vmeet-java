package com.socketserver.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author LvXinming
 * @since 2023/3/18
 * <p>用户实体类，用于测试，并非用于实际中，莫BB</p>
 */

@Data
public class User {
    public User() {}
    public User(String id, String msg, String type) {
        this.id = id;
        this.msg = msg;
        this.type = type;
        this.date = new Date();
    }

    private String id;
    private String msg;
    private String type;
    private Date date;
}
