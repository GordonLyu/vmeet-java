package com.mailserver.service.impl;

import com.mailserver.service.MailService;
import com.mailserver.utils.RedisUtil;
import com.vmeetcommon.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;

/**
 * @author LvXinming
 * @since 2023/6/1
 */

@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    @Resource
    private TemplateEngine templateEngine;

    @Resource
    private RedisUtil redisUtil;

    @Override
    public Result registerMailCode(String to) {
        Result result;
        try {
            if(redisUtil.hasKey("isAllowSend-" + to)){
                Long time = redisUtil.getExpire("isAllowSend-" + to);
                return Result.fail(400,"请在" + time + "秒后再次发送",time);
            }
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(from);
            message.setTo(to);
            message.setSubject("注册验证码");
            Context context = new Context();
            // 5位数字验证码
            String code = Integer.valueOf(10000 + (int)Math.floor(Math.random() * (100000 - 10000))).toString();
            redisUtil.set("verify-" + to, code, 5 * 60);
            redisUtil.set("isAllowSend-" + to, "not allow", 60);
            context.setVariable("code", code);
            String template = templateEngine.process("mailTemplate", context);
            message.setText(template);
            mailSender.send(message);
            result = Result.success("邮件发送成功");
        }catch (Exception e){
            redisUtil.del(to);
            result = Result.fail("邮件发送失败，服务器发生了错误");
        }
        return result;
    }

    @Override
    public Result registerVerifyCode(String email, String code) {
        if (!redisUtil.hasKey("verify-" + email)){
            return Result.fail(400,"验证码已过期，请重新发送验证码到邮箱");
        }
        String value = (String) redisUtil.get("verify-" + email);
        if(code.equals(value)){
            redisUtil.del("verify-" + email);
            return Result.success("验证成功");
        }else {
            return Result.fail("验证码错误");
        }
    }
}
