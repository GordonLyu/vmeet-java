package com.vmeetserver.client;

import com.vmeetcommon.utils.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author LvXinming
 * @since 2023/6/23
 */

@FeignClient("mail-server")
public interface MailClient {
    // 发送注册验证码
    @PostMapping(value = "/mail/register/send")
    Result registerMailCode(@RequestParam String to);

    // 验证注册验证码
    @PostMapping("/mail/register/verify")
    Result registerVerifyMailCode(@RequestParam String email,@RequestParam String code);

    // 发送登录验证码
    @PostMapping(value = "/mail/login/send")
    Result loginMailCode(@RequestParam String to);

    // 验证登录验证码
    @PostMapping("/mail/login/verify")
    Result loginVerifyMailCode(@RequestParam String email,@RequestParam String code);

    // 发送修改邮箱验证码
    @PostMapping(value = "/mail/change-mail/send")
    Result changeMailCode(@RequestParam String to);

    // 验证修改邮箱验证码
    @PostMapping("/mail/change-mail/verify")
    Result changeMailVerifyCode(@RequestParam String email,@RequestParam String code);
}
