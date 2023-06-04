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
public class MomentComments implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 评论id
     */
      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 朋友圈id
     */
    private Integer momentId;

    /**
     * 评论用户id
     */
    private Integer userId;

    /**
     * 评论内容
     */
    private String comment;

    /**
     * 评论时间
     */
    private Date comTimestamp;


}
