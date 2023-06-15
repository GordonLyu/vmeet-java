package com.vmeetserver.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author LvXinming
 * @since 2023/6/6
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PageMessageVo {
    private Integer id;
    private Integer current;
    private Integer size;
}
