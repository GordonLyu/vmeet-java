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
public class Moments implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 朋友圈id
     */
      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 发布者用户id
     */
    private Integer userId;

    /**
     * 朋友圈内容
     */
    private String momentsContent;

    /**
     * 朋友圈图片url
     */
    private String momentsImages;

    /**
     * 朋友圈视频url
     */
    private String momentsVideo;

    /**
     * 发布时间
     */
    private Date momentsTimestamp;


}
