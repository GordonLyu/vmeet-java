package com.vmeetserver.service;

import com.vmeetcommon.utils.Result;
import com.vmeetserver.entity.Message;
import com.vmeetserver.entity.vo.AddMessageVo;
import com.vmeetserver.entity.vo.PageMessageVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 像风如你
 * @since 2023-05-22
 */
public interface MessageService{

    /**
     * 获取用户之间的所有消息
     */
    Result getMessage(Integer uid);

    /**
     * 倒序分页获取户之间的消息
     */
    Result pageMessage(Integer loginId, PageMessageVo pageMessageVO);

    /**
     * 获取用户之间的最后一条消息
     */
    Result getLastMessage(Integer loginId, Integer receiverId);

    /**
     * 存储用户消息
     */
    Result addMessage(AddMessageVo addMessageVo);

    /**
     * 删除聊天消息
     */
    Result deleteMessage(Integer mid);
}
