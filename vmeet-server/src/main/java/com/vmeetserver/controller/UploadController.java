package com.vmeetserver.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.img.ImgUtil;
import cn.hutool.crypto.digest.MD5;
import com.vmeetcommon.utils.FileUploadUtil;
import com.vmeetcommon.utils.Result;
import com.vmeetserver.config.upload.AppProperties;
import com.vmeetserver.service.UploadService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Objects;

/**
 * @author 像风如你
 * @since 2023/5/23
 */
@RestController
@Slf4j
@RequestMapping("/upload")
public class UploadController {

    @Autowired
    UploadService uploadService;

    @Autowired
    AppProperties appProperties;

    @SaCheckLogin
    @PostMapping(value = "/avatar")
    public Result upload(@RequestParam(value = "File", required = false) MultipartFile multiFile) {
        Integer id = StpUtil.getLoginIdAsInt();
        try {
            Pair<Boolean, String> pair = checkFile(multiFile);
            if (!pair.getLeft()) {
                return Result.fail(400, pair.getRight());
            }
            String fileName = "avatar-" + id;
//            String fileSuffix = Objects.requireNonNull(multiFile.getOriginalFilename())
//                    .substring(multiFile.getOriginalFilename().lastIndexOf('.'));
            fileName = MD5.create().digestHex16(fileName) + ".jpg";
            boolean b = FileUploadUtil.uploadToServer(multiFile, appProperties.getUploadPath()+"avatar/", fileName);
            if (!b) {
                return Result.fail(500, "上传失败");
            }
            return uploadService.uploadAvatar("/img/avatar/" + fileName, id);
        } catch (Exception e) {
            log.error("系统异常e:", e);
            return Result.fail(500, "上传失败");
        }
    }

    public Pair<Boolean, String> checkFile(MultipartFile multiFile) {
        if (multiFile.isEmpty()) {
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
        if (!ArrayUtils.contains(appProperties.getFileTypeArray(), suffix)) {
            log.info("不支持该类型文件上传");
            return Pair.of(false, "不支持该类型文件上传");
        }
        double fileSize = size / 1024.0;//单位kb
        if (fileSize > appProperties.getMaxFileSize()) {
            log.info("文件大小超过限制");
            return Pair.of(false, "文件大小超过限制");
        }
        return Pair.of(true, "验证通过");
    }

}
