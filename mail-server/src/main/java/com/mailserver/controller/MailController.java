package com.mailserver.controller;

import com.mailserver.entity.EmailMessage;
import com.mailserver.service.MailService;
import com.mailserver.utils.RedisUtil;
import com.vmeetcommon.utils.Result;
import feign.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author LvXinming
 * @since 2023/6/1
 */

@RestController
@RequestMapping("/mail")
public class MailController {
    @Resource
    private MailService mailService;

    // 发送邮箱
    @PostMapping("/register/send-code")
    Result registerMailCode(@RequestBody EmailMessage emailMessage){
        if(emailMessage == null){
            return Result.fail(400,"邮箱不能为空");
        }else if(!emailMessage.getTo().matches("^[A-Za-z0-9]+([-._][A-Za-z0-9]+)*@[A-Za-z0-9]+(-[A-Za-z0-9]+)*(\\.[A-Za-z]{2,6}|[A-Za-z]{2,4}\\.[A-Za-z]{2,3})$")){
            return Result.fail(400,"邮箱格式不正确");
        }
        return mailService.registerMailCode(emailMessage.getTo());
    }

    @GetMapping("/register/verify-code")
    Result registerVerifyCode(String email, String code){
        if(email == null || email.equals("")){
            return Result.fail(400,"邮箱不允许为空");
        }else if(code == null || code.equals("")){
            return Result.fail(400,"验证码不允许为空");
        }
        return mailService.registerVerifyCode(email, code);
    }

    // 测试接口-是否正常请求
    @GetMapping("/test")
    String test(){
        return "测试";
    }
}
