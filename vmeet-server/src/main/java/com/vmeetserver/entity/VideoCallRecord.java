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
public class VideoCallRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 视频通话记录id
     */
      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 呼叫者id
     */
    private Integer videoCallerId;

    /**
     * 接收者id
     */
    private Integer videoReceiverId;

    /**
     * 通话开始时间
     */
    private String startTime;

    /**
     * 通话结束时间
     */
    private String endTime;

    /**
     * 通话时长（单位：秒）
     */
    private Integer duration;


}
