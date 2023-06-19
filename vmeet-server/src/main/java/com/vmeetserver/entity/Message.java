package com.vmeetserver.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * <p>
 * 
 * </p>
 *
 * @author 像风如你
 * @since 2023-05-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ToString
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 聊天消息id
     */
      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

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

    /**
     * 发送时间
     */
    private String timestamp;


}
