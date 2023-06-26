package com.fileserver.controller;

import com.fileserver.service.FileService;
import com.vmeetcommon.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author LvXinming
 * @since 2023/6/25
 */

@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping(value = "/update/avatar")
    public Result upload(@RequestParam(value = "file", required = false) MultipartFile multiFile, Integer id) {
        return fileService.uploadAvatar(multiFile, id);
    }
}
