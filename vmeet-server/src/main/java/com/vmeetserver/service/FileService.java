package com.vmeetserver.service;

import com.vmeetcommon.utils.Result;
import com.vmeetserver.entity.Message;
import com.vmeetserver.entity.vo.AddMessageVo;

/**
 * @author 像风如你
 * @since 2023/5/23
 */

public interface FileService {

    // 上传头像
    Result uploadAvatar(String path,Integer id);

    // 储存文件消息
    Result addFileMessage(AddMessageVo addMessageVo, String originalFilename);

    Message getFileMessage(Integer messageId);

}
