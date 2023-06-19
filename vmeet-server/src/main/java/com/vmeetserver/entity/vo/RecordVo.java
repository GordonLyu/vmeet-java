package com.vmeetserver.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;

/**
 * @author 像风如你
 * @since 2023/6/16
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RecordVo {
    private Integer callerId;
    private Integer receiverId;
    private String startTime;
    private String endTime;
    private Integer duration;
}

