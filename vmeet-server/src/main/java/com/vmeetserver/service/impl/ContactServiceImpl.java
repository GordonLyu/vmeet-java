package com.vmeetserver.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.vmeetcommon.utils.Result;
import com.vmeetserver.entity.User;
import com.vmeetserver.entity.dto.ContactDto;
import com.vmeetserver.entity.vo.ContactVo;
import com.vmeetserver.mapper.ContactMapper;
import com.vmeetserver.mapper.UserMapper;
import com.vmeetserver.service.ContactService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * <p>
 * 联系人表	 服务实现类
 * </p>
 *
 * @author 像风如你
 * @since 2023-05-22
 */
@Service
@Slf4j
public class ContactServiceImpl implements ContactService {

    @Autowired
    ContactMapper contactMapper;

    @Autowired
    UserMapper userMapper;

    @Override
    public Result getContact(Integer userId){
        List<Integer> uidList = contactMapper.getContact(userId);
        List<ContactDto> contactsDto = userMapper.selectContacts(uidList);
        return Result.success(contactsDto);
    }

    @Override
    public Result addContact(ContactVo contactVo) {
        User user = userMapper.SelectUser(contactVo.getUsername());
        if (user == null) {
            return Result.fail("用户不存在");
        }
        int loginId = StpUtil.getLoginIdAsInt();
        int i = contactMapper.addContact(loginId, user.getId());
        if (i <= 0) {
            return Result.fail(500, "插入失败");
        }
        log.info("添加联系人"+contactVo.getUsername()+"成功");
        return Result.success();
    }

    @Override
    public Result deleteContact(Integer userId,Integer id){
        int i = contactMapper.deleteContact(userId,id);
        if (i <= 0) {
            return Result.fail(400, "操作失败");
        }
        return Result.success("删除成功");
    }

    @Override
    public Result searchUser(String username){
        ContactDto contactDto = userMapper.searchUser(username);
        if (contactDto == null) {
            return Result.fail("用户不存在");
        }
        return Result.success(contactDto);
    }
}
