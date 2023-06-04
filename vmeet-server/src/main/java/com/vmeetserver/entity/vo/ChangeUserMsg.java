package com.vmeetserver.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 像风如你
 * @since 2023/5/26
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangeUserMsg {
    private Integer id;
    private String oldPassword;
    private String newPassword;
    private String newNickname;
}
