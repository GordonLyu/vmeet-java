package com.vmeetserver.controller;


import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import com.vmeetcommon.utils.Result;
import com.vmeetserver.entity.vo.ContactVo;
import com.vmeetserver.service.ContactService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

/**
 * <p>
 * 联系人表	 前端控制器
 * </p>
 *
 * @author 像风如你
 * @since 2023-05-22
 */

@Slf4j
@RestController
@RequestMapping("/contact")
public class ContactController {

    @Autowired
    ContactService contactService;


    // 获取所有联系人
    @SaCheckLogin
    @GetMapping("")
    public Result getContact(){
        int loginId = StpUtil.getLoginIdAsInt();
        return contactService.getContact(loginId);
    }


    // 获取一位联系人信息
    @SaCheckLogin
    @GetMapping("{uid}")
    public Result getOneContact(@PathVariable Integer uid){
        int loginId = StpUtil.getLoginIdAsInt();
        return contactService.getOneContact(uid);
    }

    // 获取待接受添加联系人列表
    @SaCheckLogin
    @GetMapping("/wait/add")
    Result getWaitAddContactList(){
        Integer loginId = StpUtil.getLoginIdAsInt();
        return contactService.getWaitAddContactList(loginId);
    }

    // 获取申请待被同意联系人列表
    @SaCheckLogin
    @GetMapping("/wait/agree")
    Result getAppliedAddContactList(){
        Integer loginId = StpUtil.getLoginIdAsInt();
        return contactService.getAppliedAddContactList(loginId);
    }

    // 添加联系人
    @SaCheckLogin
    @PostMapping("/add")
    public Result addContact(@RequestBody ContactVo contactVo){
        Integer login = StpUtil.getLoginIdAsInt();
        return contactService.addContact(login, contactVo);
    }

    // 删除联系人
    @SaCheckLogin
    @DeleteMapping("/{contactUid}")
    public Result deleteContact(@PathVariable Integer contactUid){
        Integer loginId = StpUtil.getLoginIdAsInt();
        return contactService.deleteContact(loginId, contactUid);
    }

    // 查询用户
    @SaCheckLogin
    @GetMapping("/search-user")
    public Result searchUser(@RequestParam("username") String username){
        return contactService.searchUser(username);
    }

}

