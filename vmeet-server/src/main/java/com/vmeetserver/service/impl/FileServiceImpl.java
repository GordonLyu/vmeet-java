package com.vmeetserver.service.impl;

import com.vmeetcommon.utils.EmojiUtil;
import com.vmeetcommon.utils.Result;
import com.vmeetserver.entity.Message;
import com.vmeetserver.entity.vo.AddMessageVo;
import com.vmeetserver.mapper.MessageMapper;
import com.vmeetserver.mapper.UserMapper;
import com.vmeetserver.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 像风如你
 * @since 2023/5/23
 */

@Service
@Slf4j
public class FileServiceImpl implements FileService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    MessageMapper messageMapper;

    @Override
    public Result uploadAvatar(String path,Integer id) {
        log.info("路径： " + path);
//        用户头像上传
        userMapper.upload(path,id);
        return Result.success(200, "头像上传成功" ,path);
    }


    @Override
    public Result addFileMessage(AddMessageVo addMessageVo, String originalFilename) {
        addMessageVo.setContent(EmojiUtil.encode(addMessageVo.getContent()));
        messageMapper.insertMessageAndFilename(addMessageVo, originalFilename);
        return Result.success(200, "发送文件成功", addMessageVo.getContent());
    }

    @Override
    public Message getFileMessage(Integer messageId) {
        Message message = messageMapper.getOneMessageById(messageId);
        if(!"file".equals(message.getType())){
            return null;
        }
        return messageMapper.getOneMessageById(messageId);
    }
}
