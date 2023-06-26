package com.fileserver.service;

import com.vmeetcommon.utils.Result;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author LvXinming
 * @since 2023/6/26
 */

public interface FileService {
    /**
     * 上传或更新头像
     * @param file 文件
     * @param id 用户ID
     * @return 访问url地址
     */
    Result uploadAvatar(MultipartFile file, Integer id);
}
