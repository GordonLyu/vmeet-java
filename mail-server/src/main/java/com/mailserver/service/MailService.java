package com.mailserver.service;

import com.vmeetcommon.utils.Result;

/**
 * @author LvXinming
 * @since 2023/6/1
 */
public interface MailService {

    /**
     * 发送验证码到目标邮箱
     * @param to 目标邮箱地址
     */
    Result sendMailCode(String to, String  title, String optionName);

    /**
     * 验证邮箱验证码
     * @param email 邮箱
     * @param code 用户填写的验证码
     */
    Result verifyMailCode(String email, String code);

    /**
     * 发送邮箱通知到目标邮箱
     * @param to 目标邮箱地址
     */
    Result sendChangeMailNotification(String to);
}
