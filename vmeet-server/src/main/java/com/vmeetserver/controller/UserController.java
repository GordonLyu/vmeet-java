package com.vmeetserver.controller;


import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import com.vmeetcommon.utils.Result;
import com.vmeetserver.entity.User;
import com.vmeetserver.entity.vo.ChangeUserMsg;
import com.vmeetserver.entity.vo.SignUpVo;
import com.vmeetserver.mapper.UserMapper;
import com.vmeetserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;


/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author 像风如你
 * @since 2023-05-22
 */
@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

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

    // 验证是否登录 ?
    @SaCheckLogin
    @GetMapping("/is-login")
    Result isLogin (){
        return Result.success();
    }

    // 获取一位用户的基本信息 - 未完成
    @SaCheckLogin
    @GetMapping("/{id}")
    Result getOneUser(@PathVariable @NotNull Integer id){
        return Result.success();
    }
}

