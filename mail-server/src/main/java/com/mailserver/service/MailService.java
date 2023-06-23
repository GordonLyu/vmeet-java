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
    Result registerMailCode(String to);

    /**
     * 验证邮箱验证码
     * @param email 邮箱
     * @param code 用户填写的验证码
     */
    Result registerVerifyCode(String email, String code);
}
