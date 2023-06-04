package com.mailserver.controller;

import com.mailserver.entity.EmailMessage;
import com.mailserver.service.MailService;
import com.vmeetcommon.utils.Result;
import feign.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author LvXinming
 * @since 2023/6/1
 */

@CrossOrigin
@RestController
@RequestMapping("/mail")
public class MailController {
    @Resource
    private MailService mailService;

    @Value("${spring.mail.username}")
    private String from;

    @PostMapping("/send")
    Result sendMail(@RequestBody EmailMessage emailMessage){
        if(emailMessage == null || "".equals(emailMessage)){
            return Result.fail(400,"邮箱不能为空");
        }else if(emailMessage.getTo().matches("^[A-Za-z0-9]+([-._][A-Za-z0-9]+)*@[A-Za-z0-9]+(-[A-Za-z0-9]+)*(\\.[A-Za-z]{2,6}|[A-Za-z]{2,4}\\.[A-Za-z]{2,3})$")){
            return Result.fail(400,"邮箱格式不正确");
        }
        return mailService.sendCodeToMail(emailMessage.getTo());
    }

    // 测试接口-是否正常请求
    @GetMapping("/test")
    String test(){
        return "测试";
    }
}
