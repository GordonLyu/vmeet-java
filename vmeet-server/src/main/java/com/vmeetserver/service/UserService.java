package com.vmeetserver.service;

import com.vmeetcommon.utils.Result;
import com.vmeetserver.entity.User;
import com.vmeetserver.entity.vo.ChangeUserMsg;
import com.vmeetserver.entity.vo.SignUpVo;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author 像风如你
 * @since 2023-05-22
 */
public interface UserService {

    Result login(User user);

    Result register(SignUpVo user);

    Result testRecoveryPassword(Integer uid);

    Result changePassword(ChangeUserMsg user);

    Result changeNickname(ChangeUserMsg user);

    Result getOneUser(Integer id);

}
