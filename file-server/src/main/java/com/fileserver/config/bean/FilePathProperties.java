package com.fileserver.config.bean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author LvXinming
 * @since 2023/6/26
 */

@Data
@Component
@ConfigurationProperties("file.path")
public class FilePathProperties {
    /**
     * 存放头像路径
     */
    private String avatar;
}
