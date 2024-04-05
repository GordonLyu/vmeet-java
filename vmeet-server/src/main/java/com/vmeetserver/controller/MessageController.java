package com.vmeetserver.controller;


import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import com.vmeetcommon.utils.Result;
import com.vmeetserver.entity.Message;
import com.vmeetserver.entity.vo.AddMessageVo;
import com.vmeetserver.entity.vo.PageMessageVo;
import com.vmeetserver.service.MessageService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 像风如你
 * @since 2023-05-22
 */
@RestController
@RequestMapping("/message")
public class MessageController {

    @Resource
    private MessageService messageService;

    // 倒序分页获取消息
    @SaCheckLogin
    @GetMapping("/page")
    Result pageMessage(PageMessageVo pageMessageVO){
        if(pageMessageVO.getCurrent() < 0){
            return Result.fail(400, "页码应不小于1！");
        }
        Integer loginId = StpUtil.getLoginIdAsInt();
        return messageService.pageMessage(loginId, pageMessageVO);
    }

    // 获取用户之间的最后一条消息
    @SaCheckLogin
    @GetMapping("/last/{uid}")
    Result getLastMessage(@PathVariable Integer uid){
        Integer loginId = StpUtil.getLoginIdAsInt();
        return messageService.getLastMessage(loginId, uid);
    }

    // 存储用户消息
    @SaCheckLogin
    @PutMapping("")
    Result addMessage(@RequestBody AddMessageVo addMessageVo){
        // 内容大于300
        if(addMessageVo.getContent().length()>300){
            return Result.fail(400,"内容不能超过300个字符！");
        }
        addMessageVo.setSenderId(StpUtil.getLoginIdAsInt());
        return messageService.addMessage(addMessageVo);
    }

    //删除聊天消息
    @SaCheckLogin
    @DeleteMapping("/{mid}")
    Result deleteMessage(@PathVariable Integer mid){
        return messageService.deleteMessage(mid);
    }

}

