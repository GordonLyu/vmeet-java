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
    Result sendCodeToMail(String to);
}
