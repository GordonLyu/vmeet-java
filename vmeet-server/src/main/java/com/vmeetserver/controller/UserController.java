package com.vmeetserver.controller;


import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.fastjson2.JSON;
import com.vmeetcommon.utils.Result;
import com.vmeetserver.client.MailClient;
import com.vmeetserver.entity.User;
import com.vmeetserver.entity.vo.ChangeUserMsg;
import com.vmeetserver.entity.vo.SignUpVo;
import com.vmeetserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;


/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author 像风如你
 * @since 2023-05-22
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Resource
    MailClient mailClient;

    // 注册
    @PostMapping("/register")
    public Result register(@RequestBody SignUpVo user) {
        return userService.register(user);
    }

    // 登录
    @PostMapping("/login")
    public Result login(@Validated @RequestBody User user) {
        return userService.login(user);
    }

    // 忘记密码 - 未完成
    @PostMapping("/forget-password")
    Result forgetPassword(String newPassword){
        return Result.success();
    }

    // 恢复默认密码：123456 - 测试
    @PostMapping("/test/recovery-password/{uid}")
    Result testRecoveryPassword(@PathVariable Integer uid){
        return userService.testRecoveryPassword(uid);
    }

    // 修改密码
    @SaCheckLogin
    @PostMapping("/change-password")
    public Result ChangePassword(@RequestBody ChangeUserMsg user) {
        if (user.getOldPassword() != null) {
            user.setId(StpUtil.getLoginIdAsInt());
            return userService.changePassword(user);
        }
        return Result.fail("用户名或密码错误");
    }

    // 修改昵称
    @SaCheckLogin
    @PutMapping("/change-nickname")
    public Result changeNickName(@RequestBody ChangeUserMsg user){
        user.setId(StpUtil.getLoginIdAsInt());
        return userService.changeNickname(user);
    }

    // 验证是否登录
    @SaCheckLogin
    @GetMapping("/is-login")
    Result isLogin (){
        Integer loginId = StpUtil.getLoginIdAsInt();
        return userService.getOneUser(loginId);
    }

    // 获取一位用户的基本信息 - 未完成
    @SaCheckLogin
    @GetMapping("/{id}")
    Result getOneUser(@PathVariable @NotNull Integer id){
        return Result.success();
    }

    // 发送注册邮箱验证码
    @PostMapping("/register/send-mail")
    Result registerSendMailCode(String to){
        return mailClient.registerMailCode(to);
    }

    // 验证注册邮箱验证码
    @PostMapping("/register/verify-mail")
    Result registerVerifyMailCode(String email, String code){
        return mailClient.registerVerifyMailCode(email, code);
    }

    // 发送登录邮箱验证码
    @PostMapping("/login/send-mail")
    Result loginSendMailCode(String to){
        if(!userService.isExistEmail(to)){
            return Result.fail(400, "该邮箱未被注册或绑定");
        }
        return mailClient.loginMailCode(to);
    }

    // 验证登录邮箱验证码
    @PostMapping("/login/verify-mail")
    Result loginVerifyMailCode(String email, String code){
        Result result = mailClient.loginVerifyMailCode(email, code);
        if(result.getCode() == 200){
            result = userService.loginByEmail(email);
        }
        return result;
    }

    // 发送修改邮箱验证码
    @SaCheckLogin
    @PostMapping("/change-mail/send-mail")
    Result changeMailSendCode(String to){
        if(userService.isExistEmail(to)){
            return Result.fail(400,"该邮箱已被其他账号绑定");
        }
        User user = userService.getOneUserAllInfo(StpUtil.getLoginIdAsInt());
        if(user.getEmail() == null || "".equals(user.getEmail())){
            return mailClient.changeMailCode(to);
        }
        return mailClient.changeMailCode(user.getEmail());
    }

    // 验证修改邮箱验证码
    @SaCheckLogin
    @PostMapping("/change-mail/verify-mail")
    Result changeMailVerifyCode(String email, String code){
        Result result;
        User user = userService.getOneUserAllInfo(StpUtil.getLoginIdAsInt());
        if(user.getEmail() == null || "".equals(user.getEmail())){
            result = mailClient.changeMailVerifyCode(email, code);
        } else{
            result = mailClient.changeMailVerifyCode(user.getEmail(), code);
        }
        if(result.getCode() == 200){
            return userService.changeEmail(email);
        }
        return result;
    }
}

