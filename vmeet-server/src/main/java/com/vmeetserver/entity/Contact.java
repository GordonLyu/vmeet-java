package com.vmeetserver.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 联系人表	
 * </p>
 *
 * @author 像风如你
 * @since 2023-05-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Contact implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 联系人ID
     */
      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户ID(外键关联用户表的id字段)
     */
    private Integer userId;

    /**
     * 联系人用户ID
     */
    private Integer contactUserId;


}
