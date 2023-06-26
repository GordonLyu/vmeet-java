package com.vmeetserver.client;

import com.vmeetcommon.utils.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

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
}
