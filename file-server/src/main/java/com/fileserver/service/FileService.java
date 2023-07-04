package com.fileserver.service;

import com.vmeetcommon.utils.Result;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

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

    /**
     * 用户发送文件到另一个用户
     * @param file 文件
     * @param senderId 发送者ID
     * @param receiverId 接收者ID
     */
    Result sendFile(MultipartFile file, Integer senderId, Integer receiverId);

    Result downloadMessageFile(String fileURL, String filename, HttpServletResponse response) throws UnsupportedEncodingException;
}
