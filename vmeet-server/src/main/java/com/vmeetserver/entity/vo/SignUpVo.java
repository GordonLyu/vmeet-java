package com.vmeetserver.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;


/**
 * @author 像风如你
 * @since 2023/5/22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpVo {
    private Integer id;
    private String username;
    private String password;
    private String nickname;
    private String avatar;
}

