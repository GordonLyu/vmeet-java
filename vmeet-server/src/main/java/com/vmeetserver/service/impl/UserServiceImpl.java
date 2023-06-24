package com.vmeetserver.service.impl;

import cn.dev33.satoken.stp.SaLoginConfig;
import cn.dev33.satoken.stp.StpUtil;
import com.vmeetcommon.utils.Result;
import com.vmeetcommon.utils.JasyptEncryptorUtils;
import com.vmeetserver.entity.User;
import com.vmeetserver.entity.dto.UserDto;
import com.vmeetserver.entity.vo.ChangeUserMsg;
import com.vmeetserver.entity.vo.SignUpVo;
import com.vmeetserver.mapper.UserMapper;
import com.vmeetserver.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author 像风如你
 * @since 2023-05-22
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public Result login(User reqUser) {
        User user = userMapper.SelectUser(reqUser.getUsername());

        if (user == null) {
            return Result.fail("用户不存在");
        }
        if(user.getEmail() != null){
            String emailPrefix = user.getEmail().substring(0,user.getEmail().lastIndexOf('@'));
            String emailSuffix = user.getEmail().substring(user.getEmail().lastIndexOf('@'));
            String t;
            if(emailPrefix.length()>4){
                t = emailPrefix.substring(0, 3) + emailPrefix.substring(3).replaceAll(".", "*");
            }else{
                t = emailPrefix.charAt(0) + emailPrefix.substring(1).replaceAll(".", "*");
            }
            user.setEmail(t + emailSuffix);
        }
//        执行sa-token登录
        StpUtil.login(user.getId(), SaLoginConfig.setExtra("username", user.getUsername()));
        String decodePassword = JasyptEncryptorUtils.decode(user.getPassword());
        if (decodePassword.equals(reqUser.getPassword())) {
//          使用modelMapper转换类型至DTO层
            ModelMapper modelMapper = new ModelMapper();
            UserDto userDto = modelMapper.map(user, UserDto.class);
//            添加Token信息并返回前端
            userDto.setToken(StpUtil.getTokenValue());
            log.info("用户 " + userDto.getNickname() + " 登录成功");
            return Result.success("登录成功", userDto);
        }
        return Result.fail("密码错误");
    }

    @Override
    public Result loginByEmail(String email) {
        User user = userMapper.selectUserByEmail(email);
        if(user == null){
            return Result.fail(400, "该邮箱未被注册或绑定");
        }
        user.setPassword(JasyptEncryptorUtils.decode(user.getPassword()));
        return login(user);
    }

    @Override
    public Result register(SignUpVo user) {
        user.setPassword(JasyptEncryptorUtils.encode(user.getPassword()));
        User user1 = userMapper.SelectUser(user.getUsername());
        if(user1 != null){
            return Result.fail("用户名已存在");
        }
        // 用户没上传头像时，用默认头像
        if ("".equals(user.getAvatar()) || user.getAvatar() == null) {
            int randomNumber = (int)Math.ceil(Math.random()*5);
            user.setAvatar("/img/avatar/default-"+randomNumber+".jpg");
        }
        int i = userMapper.insertUser(user);
        if (i <= 0) {
            return Result.fail(500, "插入失败");
        }
        int j = userMapper.upload(user.getAvatar(), user.getId());
        if (j <= 0) {
            log.error("上传成功，但数据更新失败");
            return Result.fail(500, "上传成功，但数据更新失败");
        }
        log.info(user.toString());
        return Result.success();
    }

    // 恢复密码；123456
    @Override
    public Result testRecoveryPassword(Integer uid) {
        String password = JasyptEncryptorUtils.encode("123456");
        userMapper.changePassword(password,uid);
        return Result.success();
    }

    @Override
    public Result changePassword(ChangeUserMsg reqUser){
        User user = userMapper.SelectUserById(reqUser.getId());
        if (user == null) {
            return Result.fail("用户不存在");
        }
//        校验旧密码对不对
        String decodePassword = JasyptEncryptorUtils.decode(user.getPassword());
        if (decodePassword.equals(reqUser.getOldPassword())) {
//            用户正确，允许修改密码
            String newPassword = JasyptEncryptorUtils.encode(reqUser.getNewPassword());
            int i = userMapper.changePassword(newPassword,user.getId());
            if(i!=0){
                log.info("用户 "+user.getNickname()+" 修改密码成功");
                return Result.success("修改成功");
            }
            return Result.fail("修改失败");
        }
        return Result.fail("密码错误");
    }

    @Override
    public Result changeNickname(ChangeUserMsg reqUser){
        User user = userMapper.SelectUserById(reqUser.getId());
        if (user == null) {
            return Result.fail("用户不存在");
        }
//      用户正确，允许修改昵称
        String newNickName = reqUser.getNewNickname();
        int i = userMapper.changeNickName(newNickName,user.getId());
        if(i!=0){
            log.info("用户 "+user.getNickname()+" 修改昵称成功");
            return Result.success("修改成功");
        }
        return Result.fail("昵称修改失败");
    }

    @Override
    public Result getOneUser(Integer id) {
        User user = userMapper.SelectUserById(id);
        if(user.getEmail() != null){
            String emailPrefix = user.getEmail().substring(0,user.getEmail().lastIndexOf('@'));
            String emailSuffix = user.getEmail().substring(user.getEmail().lastIndexOf('@'));
            String t;
            if(emailPrefix.length()>4){
                t = emailPrefix.substring(0, 3) + emailPrefix.substring(3).replaceAll(".", "*");
            }else{
                t = emailPrefix.charAt(0) + emailPrefix.substring(1).replaceAll(".", "*");
            }
            user.setEmail(t + emailSuffix);
        }
        UserDto userDto = new ModelMapper().map(user, UserDto.class);
        return Result.success(userDto);
    }

    @Override
    public User getOneUserAllInfo(Integer id) {
        return userMapper.SelectUserById(id);
    }

    @Override
    public Result changeEmail(String newEmail) {
        Result result;
        if(!userMapper.isExistEmail(newEmail)){
            int i = userMapper.changeEmail(StpUtil.getLoginIdAsInt(), newEmail);
            if(i > 0){
                result = Result.success(200, "已修改邮箱", null);
            }else{
                result = Result.fail("邮箱修改失败");
            }
        }else{
            result = Result.fail(400, "绑定邮箱已存在");
        }
        return result;
    }

    @Override
    public Boolean isExistEmail(String email) {
        return userMapper.isExistEmail(email);
    }
}
