package com.vmeetserver.config.upload;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(value = "app")
public class AppProperties {


    /**
     * 上传路径
     */
    private String uploadPath = "";

    /**
     * 下载路径
     */
    private String downloadPath = "";

    /**
     * 文件类型
     */
    private String[] fileTypeArray;

    /**
     * 文件大小
     */
    private int maxFileSize;


}
