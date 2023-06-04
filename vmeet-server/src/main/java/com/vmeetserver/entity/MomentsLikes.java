package com.vmeetserver.entity;

import com.baomidou.mybatisplus.annotation.IdType;
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
public class MomentsLikes implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 点赞id
     */
      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 朋友圈id
     */
    private Integer momentId;

    /**
     * 点赞用户id
     */
    private Integer userId;


}
