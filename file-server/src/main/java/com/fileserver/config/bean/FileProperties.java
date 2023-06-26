package com.fileserver.config.bean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("file")
public class FileProperties {

    /**
     * 上传路径
     */
    private FilePathProperties path;

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
