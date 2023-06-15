package com.vmeetserver.entity;

import lombok.Data;

import java.util.List;

/**
 * @author LvXinming
 * @since 2023/6/6
 */

@Data
public class Page<T> {
    private Integer current;
    private Integer size;
    private List<T> list;
}
