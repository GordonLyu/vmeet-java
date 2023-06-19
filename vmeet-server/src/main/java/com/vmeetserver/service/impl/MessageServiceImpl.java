package com.vmeetserver.service.impl;

import com.vmeetcommon.utils.Result;
import com.vmeetserver.entity.Message;
import com.vmeetserver.entity.Page;
import com.vmeetserver.entity.vo.AddMessageVo;
import com.vmeetserver.entity.vo.PageMessageVo;
import com.vmeetserver.mapper.MessageMapper;
import com.vmeetserver.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 像风如你
 * @since 2023-05-22
 */
@Service
public class MessageServiceImpl implements MessageService {

    @Resource
    private MessageMapper messageMapper;

    @Override
    public Result getMessage(Integer uid) {
        return null;
    }

    @Override
    public Result pageMessage(Integer loginId, PageMessageVo pageMessageVO) {
        List<Message> messages = messageMapper.pageMessage(loginId, pageMessageVO);
        Page<Message> page = new Page<>();
        BeanUtils.copyProperties(pageMessageVO, page);
        page.setList(messages);
        return Result.success(page);
    }

    @Override
    public Result getLastMessage(Integer loginId, Integer receiverId) {
        PageMessageVo pageMessageVo = new PageMessageVo(receiverId, 1, 1);
        List<Message> messages = messageMapper.pageMessage(loginId, pageMessageVo);
        if (messages.size() != 0) {
            return Result.success(messages.get(0));
        }
        return Result.success();
    }

    @Override
    public Result addMessage(AddMessageVo addMessageVo) {
        messageMapper.insertMessage(addMessageVo);
        return Result.success();
    }

    @Override
    public Result deleteMessage(Message message) {
        int i = messageMapper.delMessage(message.getSenderId(), message.getReceiverId(), message.getTimestamp());
        if (i <= 0) {
            return Result.fail(400, "操作失败");
        }
        return Result.success("删除成功");
    }
}