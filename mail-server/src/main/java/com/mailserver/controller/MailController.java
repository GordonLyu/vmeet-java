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

    // 发送注册邮箱验证码
    @PostMapping("/register/send")
    Result registerMailCode(String to){
        return mailService.sendMailCode(to, "注册验证码", "注册操作");
    }

    // 验证注册邮箱验证码
    @PostMapping("/register/verify")
    Result registerVerifyCode(String email, String code){
        return mailService.verifyMailCode(email, code);
    }

    // 发送登录邮箱验证码
    @PostMapping("/login/send")
    Result loginMailCode(String to){
        return mailService.sendMailCode(to, "登录验证码", "登录操作");
    }

    // 验证登录邮箱验证码
    @PostMapping("/login/verify")
    Result loginVerifyCode(String email, String code){
        return mailService.verifyMailCode(email, code);
    }

    // 发送修改邮箱验证码
    @PostMapping("/change-mail/send")
    Result changeMailCode(String to){
        return mailService.sendMailCode(to, "修改绑定邮箱验证码", "修改绑定邮箱操作");
    }

    // 验证修改邮箱验证码
    @PostMapping("/change-mail/verify")
    Result changeVerifyCode(String email, String code){
        return mailService.verifyMailCode(email, code);
    }


    // 测试接口-是否正常请求
    @GetMapping("/test")
    String test(){
        return "测试";
    }
}
