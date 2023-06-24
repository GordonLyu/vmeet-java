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
    public Result sendMailCode(String to, String  title, String optionName) {
        Result result;
        if(to == null){
            return Result.fail(400,"邮箱不能为空");
        }else if(!to.matches("^[A-Za-z0-9]+([-._][A-Za-z0-9]+)*@[A-Za-z0-9]+(-[A-Za-z0-9]+)*(\\.[A-Za-z]{2,6}|[A-Za-z]{2,4}\\.[A-Za-z]{2,3})$")){
            return Result.fail(400,"邮箱格式不正确");
        }
        try {
            result = sendMail(5,title, to, optionName);
        }catch (Exception e){
            redisUtil.del(to);
            result = Result.fail("邮件发送失败，服务器发生了错误");
        }
        return result;
    }

    @Override
    public Result verifyMailCode(String email, String code) {
        if(email == null || email.equals("")){
            return Result.fail(400,"邮箱不允许为空");
        }else if(code == null || code.equals("")){
            return Result.fail(400,"验证码不允许为空");
        }
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

    @Override
    public Result sendChangeMailNotification(String to) {
        return null;
    }

    /**
     * 发送邮箱
     * @param codeLength 验证码长度
     * @param title 标题
     * @param to 目标邮箱
     * @param optionName 操作名称
     */
    Result sendMail (int codeLength, String title, String to, String optionName) {
        if(redisUtil.hasKey("isAllowSend-" + to)){
            Long time = redisUtil.getExpire("isAllowSend-" + to);
            return Result.fail(400,"请在" + time + "秒后再次发送",time);
        }
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(title);
        Context context = new Context();
        // 5位数字验证码
        int x = (int) (Math.pow(10, codeLength - 1));
        int y = (int) (Math.pow(10, codeLength));
        int n = x + (int)Math.floor(Math.random() * (y - x));
        String code = String.valueOf(n);
        redisUtil.set("verify-" + to, code, 5 * 60);
        redisUtil.set("isAllowSend-" + to, "not allow", 60);
        context.setVariable("code", code);
        context.setVariable("optionName", optionName);
        String template = templateEngine.process("mailTemplate", context);
        message.setText(template);
        mailSender.send(message);
        Long time = redisUtil.getExpire("isAllowSend-" + to);
        return Result.success("邮件发送成功", time);
    }
}
