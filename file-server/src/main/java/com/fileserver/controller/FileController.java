package com.fileserver.controller;

import com.fileserver.service.FileService;
import com.vmeetcommon.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

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
    public Result uploadAvatar(@RequestParam(value = "file", required = false) MultipartFile multiFile, Integer id) {
        return fileService.uploadAvatar(multiFile, id);
    }

    @PostMapping("/send/{senderId}/to/{uid}")
    public Result sendFileToOneUser(
            @RequestParam(value = "file", required = false) MultipartFile file,
            @PathVariable Integer senderId, @PathVariable Integer uid
    ) {
        return fileService.sendFile(file, senderId, uid);
    }

    @GetMapping("/download")
    public Result downloadMessageFile(String fileURL, String originalFilename, HttpServletResponse response) throws UnsupportedEncodingException {
        System.out.println("controller 下载");
        return fileService.downloadMessageFile(fileURL, originalFilename, response);
    }
}
