package com.fileserver.utils;


import com.fileserver.config.bean.FileProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @author LvXinming
 * @since 2023/6/26
 */

@Component
@Slf4j
public class FileUtil {

    @Resource
    private FileProperties fileProperties;

    /**
     * 检查文件是否合法
     * @param multiFile 文件
     */
    public Pair<Boolean, String> checkFile(MultipartFile multiFile) {
        if (multiFile == null || multiFile.isEmpty()) {
            return Pair.of(false, "文件为空");
        }
//        获取
        String filename = multiFile.getOriginalFilename();
        String contentType = multiFile.getContentType();
        if (StringUtils.isBlank(filename)) {
            return Pair.of(false, "文件名为空");
        }
        long size = multiFile.getSize();//字节
        log.info(filename + " " + multiFile.getName());
        log.info("收到的请求文件信息：原生文件名：{},文件类型：{},文件大小：{}", filename, contentType, size);
        //获取文件后缀
        String suffix = filename.substring(filename.lastIndexOf("."));
        //判断配置的文件列表里是否支持该文件类型
        log.info(suffix);
        if (!ArrayUtils.contains(fileProperties.getFileTypeArray(), suffix)) {
            log.info("不支持该类型文件上传");
            return Pair.of(false, "不支持该类型文件上传");
        }
        double fileSize = size / 1024.0;//单位kb
        if (fileSize > fileProperties.getMaxFileSize()) {
            log.info("文件大小超过限制");
            return Pair.of(false, "文件大小超过限制");
        }
        return Pair.of(true, "验证通过");
    }
}
