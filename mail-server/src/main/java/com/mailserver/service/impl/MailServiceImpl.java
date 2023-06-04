package com.mailserver.service.impl;

import com.mailserver.service.MailService;
import com.vmeetcommon.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

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

    @Override
    public Result sendCodeToMail(String to) {
        Result result;
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(from);
            message.setTo(to);
            message.setSubject("登录验证码");
            message.setText("<center><h1>以下是您的验证码：</h1></center><br/><center>15324</center>");
            mailSender.send(message);
            result = Result.success("邮件发送成功");
        }catch (Exception e){
            result = Result.fail("邮件发送发生错误");
        }
        return result;
    }
}
