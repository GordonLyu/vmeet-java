package com.vmeetserver.client;

import com.vmeetcommon.utils.Result;
import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * 文件请求客户端
 * @author LvXinming
 * @since 2023/6/26
 */

@FeignClient("file-server")
public interface FileClient {

    /**
     * 上传或更新头像到静态资源
     * @param file 文件
     * @param id 用户id
     * @return 访问静态资源url路径字符串
     */
    @PostMapping(value = "/file/update/avatar", consumes=MediaType.MULTIPART_FORM_DATA_VALUE)
    Result updateAvatar(@RequestPart MultipartFile file, @RequestParam Integer id);

    @PostMapping(value = "/file/send/{senderId}/to/{uid}", consumes=MediaType.MULTIPART_FORM_DATA_VALUE)
    Result sendFileMessage(@RequestPart MultipartFile file, @PathVariable Integer senderId, @PathVariable Integer uid);

    @GetMapping("/file/download")
    Response downloadMessageFile(@RequestParam String fileURL, @RequestParam String originalFilename);
}
