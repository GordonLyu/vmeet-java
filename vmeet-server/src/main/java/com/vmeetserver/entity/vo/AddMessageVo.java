package com.vmeetserver.entity.vo;

import lombok.Data;
import lombok.ToString;


/**
 * @author LvXinming
 * @since 2023/6/7
 */

@Data
@ToString
public class AddMessageVo {

    /**
     * 发送者id
     */
    private Integer senderId;

    /**
     * 接收者id
     */
    private Integer receiverId;

    /**
     * 消息内容
     */
    private String content;

    /**
     * 消息类型
     */
    private String type;
}
