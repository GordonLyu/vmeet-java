package com.vmeetserver.service;

import com.vmeetcommon.utils.Result;
import com.vmeetserver.entity.Contact;
import com.baomidou.mybatisplus.extension.service.IService;
import com.vmeetserver.entity.vo.ContactVo;

/**
 * <p>
 * 联系人表	 服务类
 * </p>
 *
 * @author 像风如你
 * @since 2023-05-22
 */
public interface ContactService {

    // 获取联系人
    Result getContact(Integer userId);

    // 获取一位联系人信息
    Result getOneContact(Integer uid);

    // 获取待接受添加联系人列表
    Result getWaitAddContactList(Integer loginId);

    // 获取申请待被同意联系人列表
    Result getAppliedAddContactList(Integer loginId);

    // 添加联系人
    Result addContact(Integer loginId, ContactVo contactVo);

    // 删除联系人
    Result deleteContact(Integer userId,Integer id);

    // 搜索用户
    Result searchUser(String username);
}
