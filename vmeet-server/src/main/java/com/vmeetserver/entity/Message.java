package com.vmeetserver.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
    private Integer msgSenderId;

    /**
     * 接收者id
     */
    private Integer msgReceiverId;

    /**
     * 消息内容
     */
    private String msgContent;

    /**
     * 消息类型
     */
    private String msgType;

    /**
     * 发送时间
     */
    private Date msgTimestamp;


}
