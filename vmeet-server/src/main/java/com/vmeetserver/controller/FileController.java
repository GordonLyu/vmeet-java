package com.vmeetserver.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import com.vmeetcommon.utils.Result;
import com.vmeetserver.client.FileClient;
import com.vmeetserver.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @author 像风如你
 * @since 2023/5/23
 */
@RestController
@Slf4j
@RequestMapping("/file")
public class FileController {

    @Autowired
    FileService fileService;

    @Resource
    FileClient fileClient;

    @SaCheckLogin
    @PostMapping(value = "/update/avatar")
    public Result uploadAvatar(@RequestParam(value = "file", required = false) MultipartFile file) {
        int loginId = StpUtil.getLoginIdAsInt();
        Result result = fileClient.updateAvatar(file, loginId);
        if (result.getCode() == 200){
            return fileService.uploadAvatar(result.getData().toString(), loginId);
        }
        return result;
    }

    @PostMapping("/test")
    Result test(MultipartFile file){
        System.out.println(file);
        return fileClient.updateAvatar(file, 1);
    }

}
